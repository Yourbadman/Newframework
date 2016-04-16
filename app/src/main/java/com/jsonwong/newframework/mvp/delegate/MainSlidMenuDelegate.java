package com.jsonwong.newframework.mvp.delegate;

import com.jsonwong.newframework.R;

import news.jsonwong.com.mvpframework.view.ViewDelegate;

/**
 * @author kymjs (http://www.kymjs.com/) on 11/27/15.
 */
public class MainSlidMenuDelegate extends ViewDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }
}
