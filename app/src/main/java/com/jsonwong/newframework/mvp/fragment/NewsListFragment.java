package com.jsonwong.newframework.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsonwong.greendao.ChannelItem;
import com.jsonwong.modle.NewsListBean;
import com.jsonwong.mvp.adapter.BasePullUpRecyclerAdapter;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.NewsListAdapter;
import com.jsonwong.newframework.api.http.Url;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.util.JsonUtils;
import com.jsonwong.newframework.util.ThemeSwitchUtils;
import com.jsonwong.newframework.util.UIHelper;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 新闻列表Presenter
 *
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/16 23:03
 */
public class NewsListFragment extends MainListFragment<NewsListBean> implements
        OnTabReselectListener {

    public static final String BUNDLE_CHANNLE_ID = "bundle_channle_id_listfragment";
    private Subscription cacheSubscript;
    private ChannelItem channelItem;
    private int index = 0;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null) {
            channelItem = (ChannelItem) args.getSerializable(BUNDLE_CHANNLE_ID);
//            String tag = channelItem != null ? channelItem.getChannelId() : "";

        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cacheSubscript = Observable.just(RxVolley.getCache(Url.getNewUrl(channelItem, index + "")))
                .filter(new Func1<byte[], Boolean>() {
                    @Override
                    public Boolean call(byte[] cache) {
                        return cache != null && cache.length != 0;
                    }
                })
                .map(new Func1<byte[], ArrayList<NewsListBean>>() {
                    @Override
                    public ArrayList<NewsListBean> call(byte[] bytes) {
                        return parserInAsync(bytes);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<NewsListBean>>() {
                    @Override
                    public void call(ArrayList<NewsListBean> blogs) {
                        datas = blogs;
                        adapter.refresh(datas);
                        viewDelegate.mEmptyLayout.dismiss();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    @Override
    protected ArrayList<NewsListBean> parserInAsync(byte[] reponseData) {
        List<NewsListBean> list = Collections.EMPTY_LIST;
        if (reponseData != null) {
//                    list =
//                            NewListJson.instance(getActivity()).readJsonNewModles(new String(reponseData),
//                                    Url.TopId);
            // http://c.m.163.com/nc/article/headline/T1348647853363/0-20.html
            //  http://c.m.163.com/nc/article/list/http://c.m.163.com/nc/article/list/T1348649580692/0-20.html/0-20.html
            list = new JsonUtils<NewsListBean>().json2ObjectList(new String(reponseData), NewsListBean.class, channelItem.getChannelId());
            if (list.size() != 0)
                index += 20;
        }
        return (ArrayList) list;
    }

    @Override
    protected BasePullUpRecyclerAdapter<NewsListBean> getAdapter() {
        return new NewsListAdapter(recyclerView, datas, R.layout.list_cell_news_base, R.layout.list_cell_news_photo);
    }

    @Override
    public void onBottom() {
        doRequest();
        adapter.setState(BasePullUpRecyclerAdapter.STATE_LOADING);
    }

    @Override
    public void doRequest() {
        new RxVolley.Builder().url(Url.getNewUrl(channelItem, index + ""))
                .contentType(RxVolley.Method.GET)
                .cacheTime(600)
                .callback(callBack)
                .doTask();

    }

    public void doRefresh() {
        index = 0;
        doRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cacheSubscript != null && cacheSubscript.isUnsubscribed())
            cacheSubscript.unsubscribe();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        NewsListBean news = ((NewsListBean) data);
        if (news != null) {
            UIHelper.showNewsDetail(view.getContext(), news);
            // 放入已读列表
            AppContext.putReadedPostList(Constants.PREF_READED_NEWS_LIST, news.getDocid()
                    + "", "true");
            TextView tvTitle = viewDelegate.get(R.id.tv_title);
            if (tvTitle != null) {
                tvTitle.setTextColor(AppContext.getInstance().getResources().getColor(ThemeSwitchUtils.getTitleReadedColor()));
            }

        }
    }

    private HttpParams getHttpParams(int index) {
        HttpParams params = new HttpParams();
        params.put("pageIndex", index);
        params.put("pageSize", 20);
        return params;
    }

    @Override
    public void onTabReselect() {
        doRefresh();
    }
}
