package com.jsonwong.newframework.base.mvp.presenter;


import news.jsonwong.com.mvpframework.presenter.FragmentPresenter;
import news.jsonwong.com.mvpframework.view.IDelegate;

/**
 * 主界面内容Fragment
 *
 * @author kymjs (http://www.kymjs.com/) on 11/27/15.
 */
public abstract class MainFragment<T extends IDelegate> extends FragmentPresenter<T> {

    public void onChange() {
    }
}
