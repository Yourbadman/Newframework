
package com.jsonwong.newframework.ui;

import android.os.Bundle;
import android.view.View;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseActivity;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.fragment.ChannelFragment;

/**
 * 频道管理
 */
public class ChannelActivity extends BaseActivity {
    public static String TAG = "ChannelActivity";

    //不加入到AppManage来管理Activity
    protected boolean hasAddActivity2Manage() {
        return false;
    }


    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected BaseFragment getShowFragment() {
        return new ChannelFragment();
    }

    @Override
    protected Bundle getFragmentArguments() {

        return null;
    }

    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.actionbar_title_addchannel;
    }


    @Override
    public void onClick(View v) {
    }


    @Override
    public void initData() {

    }


    @Override
    public void initView(Bundle bundle) {
        super.initView(bundle);

    }


}
