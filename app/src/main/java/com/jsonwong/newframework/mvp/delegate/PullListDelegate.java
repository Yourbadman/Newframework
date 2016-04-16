package com.jsonwong.newframework.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.mvp.delegate.BaseListDelegate;
import com.jsonwong.newframework.ui.empty.EmptyLayout;

/**
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/16 20:43
 */
public class PullListDelegate extends BaseListDelegate {

    public EmptyLayout mEmptyLayout;


    @Override
    public void initWidget() {
        super.initWidget();
        mEmptyLayout = new EmptyLayout(getActivity());
        setEmptyLayout(mEmptyLayout);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener l) {
        SwipeRefreshLayout swipeRefreshLayout = get(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(l);
    }
}
