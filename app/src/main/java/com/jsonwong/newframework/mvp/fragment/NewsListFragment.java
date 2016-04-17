package com.jsonwong.newframework.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jsonwong.modle.NewsListBean;
import com.jsonwong.mvp.adapter.BasePullUpRecyclerAdapter;
import com.jsonwong.mvp.adapter.RecyclerHolder;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.util.JsonUtils;
import com.kymjs.rxvolley.RxVolley;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cacheSubscript = Observable.just(RxVolley.getCache(""))
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

            list = new JsonUtils<NewsListBean>().json2ObjectList(new String(reponseData), NewsListBean.class, "");

        }
        return (ArrayList) list;
    }

    @Override
    protected BasePullUpRecyclerAdapter<NewsListBean> getAdapter() {
        return new BasePullUpRecyclerAdapter<NewsListBean>(recyclerView, datas, R.layout.list_cell_news_base) {
//            final View.OnClickListener imageClickListener = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    KJGalleryActivity.toGallery(getActivity(), (String) v.getTag());
//                }
//            };

            @Override
            public void convert(RecyclerHolder holder, final NewsListBean item, int position) {
//                holder.setText(R.id.item_blog_tv_title, item.getTitle());
//                holder.setText(R.id.item_blog_tv_description, item.getDescription());
//                holder.setText(R.id.item_blog_tv_author, item.getAuthor());
//                holder.setText(R.id.item_blog_tv_date, item.getPubDate());
//                if (TextUtils.isEmpty(item.getRecommend())) {
//                    holder.getView(R.id.item_blog_tip_recommend).setVisibility(View.GONE);
//                } else {
//                    holder.getView(R.id.item_blog_tip_recommend).setVisibility(View.VISIBLE);
//                }
//
//                ImageView imageView = holder.getView(R.id.item_blog_img);
//                String imageUrl = item.getImage().trim();
//                if (TextUtils.isEmpty(imageUrl)) {
//                    imageView.setVisibility(View.GONE);
//                } else {
//                    imageView.setVisibility(View.VISIBLE);
//                    new BitmapCore.Builder().url(imageUrl).view(imageView).doTask();
//                    //在列表点击图片就直接进详情了,没必要进图片预览(布局文件中已取消焦点)
////                    imageView.setOnClickListener(imageClickListener);
//                }
            }
        };
    }

    @Override
    public void doRequest() {
        new RxVolley.Builder().url("")
                .contentType(RxVolley.Method.GET)
                .cacheTime(600)
                .callback(callBack)
                .doTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cacheSubscript != null && cacheSubscript.isUnsubscribed())
            cacheSubscript.unsubscribe();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        NewsListBean blog = ((NewsListBean) data);
        // BlogDetailActivity.goinActivity(getActivity(), blog.getLink(), blog.getTitle());
    }

    @Override
    public void onTabReselect() {
        doRequest();
    }
}
