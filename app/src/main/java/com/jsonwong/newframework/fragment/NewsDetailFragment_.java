package com.jsonwong.newframework.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.bean.News;
import com.jsonwong.newframework.ui.DetailActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laucherish on 16/3/17.
 */
public class NewsDetailFragment_ extends BaseFragment {

    @InjectView(R.id.iv_header)
    SimpleDraweeView mIvHeader;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_source)
    TextView mTvSource;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @InjectView(R.id.wv_news)
    WebView mWvNews;
    @InjectView(R.id.nested_view)
    NestedScrollView mNestedView;
    ContentLoadingProgressBar mPbLoading;

    private String newId;
    private String imageUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail_;
    }


    @Override
    public void initView(View view) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setTitleEnabled(false);

        newId = getArguments().getString(DetailActivity.NEWS_ID);
        imageUrl = getArguments().getString(DetailActivity.NEWS_IMAGE_URL);

        if (imageUrl != null)
            mIvHeader.setImageURI(Uri.parse(imageUrl));

    }

    @Override
    public void initData() {

    }


    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
