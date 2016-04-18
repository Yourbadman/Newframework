package com.jsonwong.newframework.base.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.ui.empty.EmptyLayout;

import news.jsonwong.com.mvpframework.view.ViewDelegate;


/**
 * RecyclerView的List基类
 *
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/18 21:40
 */
public class BaseListDelegate extends ViewDelegate {

    protected RecyclerView mRecyclerView;
    public EmptyLayout mEmptyLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.base_list_delegate;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        SwipeRefreshLayout refreshLayout = get(R.id.swiperefreshlayout);
        refreshLayout.setColorSchemeResources(R.color.base_swiperefresh_color1,
                R.color.base_swiperefresh_color2,
                R.color.base_swiperefresh_color3,
                R.color.base_swiperefresh_color4);

        mRecyclerView = get(R.id.recyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));


        mEmptyLayout = new EmptyLayout(getActivity());
        setEmptyLayout(mEmptyLayout);


    }

    protected void setEmptyLayout(View emptyLayout) {
        mEmptyLayout.removeAllViews();
        FrameLayout layout = get(R.id.emptyLayoutParent);
        layout.addView(emptyLayout);
    }

    /**
     * 设置正在刷新的状态
     */
    public void setSwipeRefreshLoadingState() {
        SwipeRefreshLayout refreshLayout = get(R.id.swiperefreshlayout);
        refreshLayout.setRefreshing(true);
        refreshLayout.setEnabled(false);
    }

    /**
     * 设置完成刷新状态
     */
    public void setSwipeRefreshLoadedState() {
        SwipeRefreshLayout refreshLayout = get(R.id.swiperefreshlayout);
        refreshLayout.setRefreshing(false);
        refreshLayout.setEnabled(true);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) mRecyclerView.getLayoutManager();
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener l) {
        SwipeRefreshLayout swipeRefreshLayout = get(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(l);
    }
}
