package com.jsonwong.newframework.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.jsonwong.newframework.adapter.NewsAdapter;
import com.jsonwong.newframework.adapter.base.ListBaseAdapter;
import com.jsonwong.newframework.api.remote.OSChinaApi;
import com.jsonwong.newframework.base.BaseListFragment;
import com.jsonwong.newframework.bean.ListEntity;
import com.jsonwong.newframework.bean.NewModle;
import com.jsonwong.newframework.bean.NewsList;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.ui.empty.EmptyLayout;
import com.jsonwong.newframework.util.UIHelper;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 新闻资讯
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年11月12日 下午4:17:45
 */
public class NewsFragment extends BaseListFragment<NewModle> implements
        OnTabReselectListener {

    protected static final String TAG = NewsFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "newslist_";

    @Override
    protected NewsAdapter getListAdapter() {
        return new NewsAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX + mCatalog;
    }

    @Override
    protected ListEntity<NewModle> parseList(InputStream is) throws Exception {
//        NewsList list = null;
//        try {
//            list = XmlUtils.toBean(NewsList.class, is);
//        } catch (NullPointerException e) {
//            list = new NewsList();
//        }
        return null;
    }

    @Override
    protected ListEntity<NewModle> readList(Serializable seri) {
        return null;
    }

    private int index = 0;

    @Override
    protected void sendRequestData() {
        OSChinaApi.getNewsList(getNewUrl(index + ""), mCatalog, mCurrentPage, mHandler);
        mCurrentPage++;
        index = index + 20;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        NewModle news = mAdapter.getItem(position);
        if (news != null) {
            UIHelper.showNewsDetail(view.getContext(),news);

            // 放入已读列表
            saveToReadedList(view, NewsList.PREF_READED_NEWS_LIST, news.getDocid()
                    + "");
        }
    }

    @Override
    protected void executeOnLoadDataSuccess(List<NewModle> data) {
        if (mCatalog == NewsList.CATALOG_WEEK
                || mCatalog == NewsList.CATALOG_MONTH) {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            if (mState == STATE_REFRESH)
                mAdapter.clear();
            mAdapter.addData(data);
            mState = STATE_NOMORE;
            mAdapter.setState(ListBaseAdapter.STATE_NO_MORE);
            return;
        }
        super.executeOnLoadDataSuccess(data);
    }

    @Override
    public void onTabReselect() {
        onRefresh();
    }

    @Override
    protected long getAutoRefreshTime() {
        // 最新资讯两小时刷新一次
        if (mCatalog == NewsList.CATALOG_ALL) {

            return 2 * 60 * 60;
        }
        return super.getAutoRefreshTime();
    }
}
