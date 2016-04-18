package com.jsonwong.newframework.mvp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jsonwong.mvp.adapter.BasePullUpRecyclerAdapter;
import com.jsonwong.mvp.adapter.BaseRecyclerAdapter;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.mvp.delegate.BaseListDelegate;
import com.jsonwong.newframework.interf.INetWorkRequest;
import com.jsonwong.newframework.ui.empty.EmptyLayout;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.toolbox.Loger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import news.jsonwong.com.mvpframework.presenter.FragmentPresenter;

/**
 * 下拉列表的Preserter类，处理页面逻辑
 *
 * @author kymjs (http://www.kymjs.com/) on 12/3/15.
 */
public abstract class MainListFragment<T> extends FragmentPresenter<BaseListDelegate> implements
        SwipeRefreshLayout.OnRefreshListener, INetWorkRequest, BaseRecyclerAdapter.OnItemClickListener {

    protected BasePullUpRecyclerAdapter<T> adapter;
    protected RecyclerView recyclerView;
    protected List<T> datas = new ArrayList<>();

    protected abstract BasePullUpRecyclerAdapter<T> getAdapter();

    protected abstract ArrayList<T> parserInAsync(byte[] t);

    protected HttpCallback callBack = new HttpCallback() {
        private List<T> tempDatas;

        @Override
        public void onSuccessInAsync(byte[] t) {
            super.onSuccessInAsync(t);
            try {
                tempDatas = parserInAsync(t);
            } catch (Exception e) {
                tempDatas = Collections.EMPTY_LIST;
            }
        }

        @Override
        public void onSuccess(String t) {
            super.onSuccess(t);
            Loger.debug("===列表网络请求:" + t);
            if (viewDelegate != null && viewDelegate.mEmptyLayout != null) {
                if (tempDatas == null || tempDatas.isEmpty() || adapter == null || adapter
                        .getItemCount() < 1) {
                    viewDelegate.mEmptyLayout.setErrorType(EmptyLayout.NODATA);
                    adapter.setState(BasePullUpRecyclerAdapter.STATE_INVISIBLE);
                } else {
                    viewDelegate.mEmptyLayout.dismiss();
                    adapter.refresh(tempDatas);
                    datas = tempDatas;
                }
            }

        }

        @Override
        public void onFailure(int errorNo, String strMsg) {
            super.onFailure(errorNo, strMsg);
            Loger.debug("====网络请求异常" + strMsg);
            //有可能界面已经关闭网络请求仍然返回
            if (viewDelegate != null && viewDelegate.mEmptyLayout != null && adapter != null) {
                if (adapter.getItemCount() > 1) {
                    viewDelegate.mEmptyLayout.dismiss();
                } else {
                    viewDelegate.mEmptyLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                }
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            if (viewDelegate != null) {
                viewDelegate.setSwipeRefreshLoadedState();
            }
        }
    };

    @Override
    protected Class<BaseListDelegate> getDelegateClass() {
        return BaseListDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        recyclerView = viewDelegate.get(R.id.recyclerView);
        adapter = getAdapter();
        bindEven();
        viewDelegate.setOnRefreshListener(this);
        adapter.setOnItemClickListener(this);
        //doRequest();
    }

    private void bindEven() {
        viewDelegate.mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRequest();
                viewDelegate.mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //滚动到底部的监听
                    LinearLayoutManager layoutManager = viewDelegate.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastItems = layoutManager.findFirstVisibleItemPosition();
                    if ((pastItems + visibleItemCount) >= totalItemCount) {
                        onBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void onBottom() {
        adapter.setState(BasePullUpRecyclerAdapter.STATE_LOADING);
    }

    @Override
    public void onRefresh() {
        viewDelegate.setSwipeRefreshLoadingState();
        doRequest();
    }
}
