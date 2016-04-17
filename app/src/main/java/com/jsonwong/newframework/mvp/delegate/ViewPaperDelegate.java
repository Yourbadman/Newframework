package com.jsonwong.newframework.mvp.delegate;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.ui.empty.EmptyLayout;
import com.jsonwong.newframework.widget.PagerSlidingTabStrip;

import news.jsonwong.com.mvpframework.view.ViewDelegate;

/**
 * 带有导航栏的ViewPaper视图代理
 *
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/16 20:43
 */
public class ViewPaperDelegate extends ViewDelegate  {

    public PagerSlidingTabStrip mTabStrip;
    public ViewPager mViewPager;
  //  protected ViewPageFragmentAdapter mTabsAdapter;
  public EmptyLayout mErrorLayout;
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

//        mTabsAdapter = new ViewPageFragmentAdapter(activity.getChildFragmentManager(),
//                mTabStrip, mViewPager);
//        setScreenPageLimit();
//        onSetupTabAdapter(mTabsAdapter,null);
    }



}
