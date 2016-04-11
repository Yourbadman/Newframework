package com.jsonwong.newframework.delegate;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.cache.DataCleanManager;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.ui.MainTab;
import com.jsonwong.newframework.ui.NavigationDrawerFragment;
import com.jsonwong.newframework.widget.MyFragmentTabHost;

import news.jsonwong.com.mvpframework.view.AppDelegate;

/**
 * @author kymjs (http://www.kymjs.com/) on 11/6/15.
 */
public class MainDelegate extends AppDelegate implements NavigationDrawerFragment.NavigationDrawerCallbacks, TabHost.OnTabChangeListener, View.OnTouchListener {

    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    public MyFragmentTabHost mTabHost;
    private CharSequence mTitle;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private AppCompatActivity activity;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Toolbar getToolbar() {
        mToolbar = get(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException("Must include Toolbar and define @+id/toolbar." +
                    " You can get @layout/base_toolbar");
        }
        return mToolbar;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        activity = getActivity();
        //设置显示home图标
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mTabHost = get(android.R.id.tabhost);

        mNavigationDrawerFragment = (NavigationDrawerFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mTitle = activity.getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) get(R.id.drawer_layout));

        mTabHost.setup(activity, activity.getSupportFragmentManager(), R.id.realtabcontent);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        initTabs();

        // 中间按键图片触发
        // mAddBt.setOnClickListener(this);

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);


        if (AppContext.isFristStart()) {
            mNavigationDrawerFragment.openDrawerMenu();
            DataCleanManager.cleanInternalCache(AppContext.getInstance());
            AppContext.setFristStart(false);
        }


    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(activity.getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(activity.getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = activity.getResources().getDrawable(
                    mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);

            title.setText(activity.getString(mainTab.getResName()));
            tab.setIndicator(indicator);
//            tab.setContent(new TabContentFactory() {
//
//                @Override
//                public View createTabContent(String tag) {
//                    return new View(MainActivity.this);
//                }
//            });
            mTabHost.addTab(tab, mainTab.getClz(), null);

           /* if (mainTab.equals(MainTab.ME)) {
                View cn = indicator.findViewById(R.id.tab_mes);
                mBvNotice = new BadgeView(MainActivity.this, cn);
                mBvNotice.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                mBvNotice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                mBvNotice.setBackgroundResource(R.drawable.notification_bg);
                mBvNotice.setGravity(Gravity.CENTER);
            }*/
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    public boolean menuIsOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.LEFT);
    }

    /**
     * 修改侧滑菜单状态
     *
     * @return 修改后菜单的状态
     */
    public boolean changeMenuState() {
        if (mDrawerLayout == null) mDrawerLayout = get(R.id.drawer_layout);
        boolean isOpen = mDrawerLayout.isDrawerOpen(Gravity.LEFT);
        if (isOpen) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        return !isOpen;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        activity.onTouchEvent(event);
        boolean consumed = false;
        // use getTabHost().getCurrentTabView to decide if the current tab is
        // touched again
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && v.equals(mTabHost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {
        return activity.getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
//        if (tabId.equals(getString(MainTab.ME.getResName()))) {
//            mBvNotice.setText("");
//            mBvNotice.hide();
//        }
        activity.supportInvalidateOptionsMenu();
    }

//    /**
//     * 显示Toolbar的退出tip
//     */
//    public void showExitTip() {
//        TextView view = get(R.id.titlebar_text_exittip);
//        view.setVisibility(View.VISIBLE);
//        Animation a = KJAnimations.getTranslateAnimation(0f, 0f, -mToolbar.getHeight(), 0f, 300);
//        view.startAnimation(a);
//    }
//
//
//    /**
//     * 取消退出
//     */
//    public void cancleExit() {
//        final TextView view = get(R.id.titlebar_text_exittip);
//        Animation a = KJAnimations.getTranslateAnimation(0f, 0f, 0f, -mToolbar.getHeight(), 300);
//        a.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//        view.startAnimation(a);
//    }
}
