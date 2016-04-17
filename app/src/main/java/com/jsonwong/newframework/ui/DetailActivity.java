package com.jsonwong.newframework.ui;

import android.os.Bundle;
import android.view.View;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseActivity;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.mvp.fragment.NewsDetailFragment_;


/**
 * 新闻详情
 *
 * @author jsonwong（http://www.jsonwong.cn）
 */
public class DetailActivity extends BaseActivity {

    public static final int DISPLAY_NEWS = 0;
    public static final String NEWS_ID = "news_id_1";
    public static final String NEWS = "news_";
    public static final String NEWS_IMAGE_URL = "news_image_url";
    public static final String BUNDLE_KEY_DISPLAY_TYPE = "BUNDLE_KEY_DISPLAY_TYPE";


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
        return new NewsDetailFragment_();
    }

    @Override
    protected Bundle getFragmentArguments() {
        Bundle bundle = null;
        if (getIntent() != null && getIntent().getExtras() != null) {
            bundle = new Bundle();
            bundle.putParcelable(DetailActivity.NEWS, getIntent().getExtras().getParcelable(DetailActivity.NEWS));
        }
        return bundle;
    }

    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.actionbar_title_detail;
    }


    @Override
    public void onClick(View v) {
    }


    @Override
    public void initData() {

    }
}
