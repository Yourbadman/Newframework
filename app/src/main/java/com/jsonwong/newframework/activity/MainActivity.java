package com.jsonwong.newframework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jsonwong.modle.base.Event;
import com.jsonwong.newframework.AppConfig;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.AppManager;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.delegate.MainDelegate;
import com.jsonwong.newframework.ui.ChannelActivity;
import com.jsonwong.newframework.util.DoubleClickExitHelper;
import com.kymjs.rxvolley.rx.RxBus;

import news.jsonwong.com.mvpframework.base.BaseFrameActivity;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 主界面
 */
public class MainActivity extends BaseFrameActivity<MainDelegate> {

    public static final String MENU_CLICK_EVEN = "slid_menu_click_event";

//    private MainFragment currentFragment; //当前内容所显示的Fragment
//    private MainFragment content1 = new BlogListFragment();
//    private MainFragment content2 = new XituFragment();
//    private MainFragment content3 = new TopListFragment();

    private Subscription rxBusSubscript;

    private DoubleClickExitHelper mDoubleClickExit;


    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mDoubleClickExit = new DoubleClickExitHelper(this);

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_content, content1, content1.getClass().getName())
//                .commit();


        rxBusSubscript = RxBus.getDefault().take(Event.class)
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        changeContent(event);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }


    /**
     * 根据MainSlidMenu点击选择不同的响应
     *
     * @param event
     */
    private void changeContent(Event event) {
        if (!MENU_CLICK_EVEN.equals(event.getAction())) return;
        View view = event.getObject();
        switch (view.getId()) {
//            case R.id.menu_item_tag1:
//                changeFragment(content1);
//                break;
//            case R.id.menu_item_tag2:
//                changeFragment(content2);
//                break;
//            case R.id.menu_item_tag3:
//                changeFragment(content3);
//                break;
//            case R.id.menu_item_tag4:
//                BlogDetailActivity.goinActivity(this, Api.OSL, null);
//                break;
            default:
                break;
        }
        viewDelegate.changeMenuState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        if (!viewDelegate.menuIsOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                //UIHelper.showSimpleBack(this, SimpleBackPage.SEARCH);
                Intent intent = new Intent(this, ChannelActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 监听返回--是否退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (viewDelegate.menuIsOpen()) {
            viewDelegate.changeMenuState();
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
            if (AppContext.get(AppConfig.KEY_DOUBLE_CLICK_EXIT, true)) {
                return mDoubleClickExit.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxBusSubscript != null && rxBusSubscript.isUnsubscribed())
            rxBusSubscript.unsubscribe();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");
    }


}
