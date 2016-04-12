package com.jsonwong.newframework.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.jsonwong.modle.base.Event;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.activity.MainActivity;
import com.jsonwong.newframework.delegate.MainSlidMenuDelegate;
import com.kymjs.rxvolley.rx.RxBus;

import news.jsonwong.com.mvpframework.presenter.FragmentPresenter;

/**
 * 侧滑界面逻辑代码
 *
 * @author kymjs (http://www.kymjs.com/) on 11/27/15.
 */
public class MainSlidMenu extends FragmentPresenter<MainSlidMenuDelegate> implements View
        .OnClickListener {

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected Class<MainSlidMenuDelegate> getDelegateClass() {
        return MainSlidMenuDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

        viewDelegate.setOnClickListener(this,
                R.id.menu_item_quests,
                R.id.menu_item_opensoft,
                R.id.menu_item_blog,
                R.id.menu_item_gitapp,
                R.id.menu_item_setting, R.id.menu_item_theme);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        new BitmapCore.Builder()
//                .view(viewDelegate.get(R.id.menu_header))
//                .url(AppConfig.getAvatarURL())
//                .errorResId(R.mipmap.def_avatar)
//                .doTask();
    }

    @Override
    public void onClick(View v) {
        Event event = new Event();
        event.setAction(MainActivity.MENU_CLICK_EVEN);
        event.setObject(v);
        RxBus.getDefault().post(event);
    }


    /**
     * Users of this fragment must call this method to set up the navigation
     * drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
//    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
//        mFragmentContainerView = getActivity().findViewById(fragmentId);
//        mDrawerLayout = drawerLayout;
//
//        // set a custom shadow that overlays the main content when the drawer
//        // opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
//                GravityCompat.START);
//        // set up the drawer's list view with items and click listener
//
//        ActionBar actionBar =  getActivity().getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//
//        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
//                null, R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close) {
//
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getActivity().invalidateOptionsMenu();
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getActivity().invalidateOptionsMenu();
//            }
//        };
//
//        mDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mDrawerToggle.syncState();
//            }
//        });
//
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//    }
}
