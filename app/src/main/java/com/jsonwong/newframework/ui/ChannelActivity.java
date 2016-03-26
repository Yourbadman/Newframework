
package com.jsonwong.newframework.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.greendao.ChannelItem;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.DragAdapter;
import com.jsonwong.newframework.adapter.OtherAdapter;
import com.jsonwong.newframework.base.BaseActivity;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.db.service.ChannelManageService;
import com.jsonwong.newframework.fragment.ChannelFragment;
import com.jsonwong.newframework.fragment.NewsDetailFragment_;
import com.jsonwong.newframework.rxbus.RxBus;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.widget.DragGrid;
import com.jsonwong.newframework.widget.OtherGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

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
