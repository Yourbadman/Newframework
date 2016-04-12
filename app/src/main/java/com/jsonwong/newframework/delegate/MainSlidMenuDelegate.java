package com.jsonwong.newframework.delegate;

import com.jsonwong.newframework.R;

import news.jsonwong.com.mvpframework.view.AppDelegate;

/**
 * @author kymjs (http://www.kymjs.com/) on 11/27/15.
 */
public class MainSlidMenuDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }
}
