package com.jsonwong.newframework.mvp.delegate;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.ViewPageFragmentAdapter;
import com.jsonwong.newframework.ui.empty.EmptyLayout;
import com.jsonwong.newframework.widget.PagerSlidingTabStrip;

import news.jsonwong.com.mvpframework.view.ViewDelegate;

/**
 * 带有导航栏的ViewPaper视图代理
 *
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/16 20:43
 */
public class ViewPaperDelegate extends ViewDelegate {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
  //  protected ViewPageFragmentAdapter mTabsAdapter;
    protected EmptyLayout mErrorLayout;
    protected AppCompatActivity activity;
    @Override
    public int getRootLayoutId() {
        return R.layout.base_viewpage_fragment;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        activity = getActivity();
        mTabStrip = get(R.id.pager_tabstrip);

        mViewPager = get(R.id.pager);

        mErrorLayout =get(R.id.error_layout);

        mTabsAdapter = new ViewPageFragmentAdapter(activity.getChildFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter,null);
    }

    protected void setEmptyLayout(View emptyLayout) {
        FrameLayout layout = get(R.id.emptyLayoutParent);
        layout.addView(emptyLayout);
    }

    public void setSwipeRefreshLoadingState() {
        SwipeRefreshLayout refreshLayout = get(R.id.swiperefreshlayout);
        refreshLayout.setRefreshing(true);
        refreshLayout.setEnabled(false);
    }

    public void setSwipeRefreshLoadedState() {
        SwipeRefreshLayout refreshLayout = get(R.id.swiperefreshlayout);
        refreshLayout.setRefreshing(false);
        refreshLayout.setEnabled(true);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) mRecyclerView.getLayoutManager();
    }
}
