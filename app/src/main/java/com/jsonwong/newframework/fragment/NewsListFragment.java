package com.jsonwong.newframework.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.jsonwong.modle.ListEntity;
import com.jsonwong.modle.NewsListBean;
import com.jsonwong.newframework.adapter.NewsListAdapter;
import com.jsonwong.newframework.api.remote.OSChinaApi;
import com.jsonwong.newframework.base.BaseListFragment;
import com.jsonwong.newframework.http.Url;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.util.UIHelper;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 新闻列表
 *
 * @author jsonwong（http://www.jsonwong.com/）
 * @created 2016年04月07日 下午4:17:45
 */
public class NewsListFragment extends BaseListFragment<NewsListBean> implements
        OnTabReselectListener {

    protected static final String TAG = NewsListFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "newslist_";

    @Override
    protected NewsListAdapter getListAdapter() {
        return new NewsListAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX + getChannelId();
    }

    @Override
    protected ListEntity<NewsListBean> parseList(InputStream is) throws Exception {

        return null;
    }

    @Override
    protected ListEntity<NewsListBean> readList(Serializable seri) {
        return null;
    }

    private int index = 0;

    @Override
    protected void sendRequestData() {

        OSChinaApi.getNewsList(Url.getNewUrl(channelItem,index + ""), mCurrentPage, mHandler);
        mCurrentPage++;
        index = index + 20;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        NewsListBean news = mAdapter.getItem(position);
        if (news != null) {
            UIHelper.showNewsDetail(view.getContext(), news);

            // 放入已读列表
            saveToReadedList(view, Constants.PREF_READED_NEWS_LIST, news.getDocid()
                    + "");
        }
    }

    @Override
    protected void executeOnLoadDataSuccess(List<NewsListBean> data) {
//        if (mCatalog == Constants.CATALOG_WEEK
//                || mCatalog == Constants.CATALOG_MONTH) {
//            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
//            if (mState == STATE_REFRESH)
//                mAdapter.clear();
//            mAdapter.addData(data);
//            mState = STATE_NOMORE;
//            mAdapter.setState(ListBaseAdapter.STATE_NO_MORE);
//            return;
//        }
        super.executeOnLoadDataSuccess(data);
    }

    @Override
    public String getChannelId() {
        return null;
    }

    @Override
    public void onTabReselect() {
        onRefresh();
    }

    @Override
    protected long getAutoRefreshTime() {
        // 最新资讯两小时刷新一次
//        if (mCatalog == Constants.CATALOG_ALL) {
//
//            return 2 * 60 * 60;
//        }
        return super.getAutoRefreshTime();
    }
}
