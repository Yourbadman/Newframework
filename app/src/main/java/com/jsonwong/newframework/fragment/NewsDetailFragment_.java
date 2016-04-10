package com.jsonwong.newframework.fragment;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.modle.NewsListBean;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.ui.DetailActivity;
import com.jsonwong.newframework.util.StringUtils;

import butterknife.InjectView;

/**
 * Created by laucherish on 16/3/17.
 */
public class NewsDetailFragment_ extends BaseFragment {

    @InjectView(R.id.iv_header)
    SimpleDraweeView mIvHeader;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @InjectView(R.id.wv_news)
    WebView mWvNews;
    @InjectView(R.id.nested_view)
    NestedScrollView mNestedView;
    @InjectView(R.id.tv_source)
    TextView source;
    @InjectView(R.id.tv_time)
    TextView time;
    @InjectView(R.id.tv_comment_count)
    TextView comment_count;


    ContentLoadingProgressBar mPbLoading;

    private NewsListBean newModle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail_;
    }


    @Override
    public void initView(View view) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        //mToolbar.setSubtitle(R.string.app_name);
        activity.setSupportActionBar(mToolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置是否Title,否则会下拉存在
        // mCollapsingToolbarLayout.setTitleEnabled(false);
        // actionBar.setSubtitle(R.string.app_name);
    }

    @Override
    public void initData() {
        newModle = getArguments().getParcelable(DetailActivity.NEWS);
        if (newModle == null)
            return;

        mIvHeader.setImageURI(Uri.parse(newModle.getImgsrc()));
        // mTvTitle.setText(newModle.getTitle());
        //mTvSource.setText(newModle.getSource());
        mCollapsingToolbarLayout.setTitle(newModle.getTitle());
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayout_title);
        source.setText(newModle.getSource());
        time.setText(StringUtils.friendly_time(newModle.getPtime()));
        comment_count.setText(newModle.getReplyCount() + "");

    }


    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
