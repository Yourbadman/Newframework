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
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.bean.News;
import com.jsonwong.newframework.ui.DetailActivity;

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
    public void afterCreate(Bundle savedInstanceState) {

        newId = getArguments().getString(DetailActivity.NEWS_ID);
        imageUrl = getArguments().getString(DetailActivity.NEWS_IMAGE_URL);

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
    }

    @Override
    public void initData() {

        if (imageUrl != null)
            mIvHeader.setImageURI(Uri.parse(imageUrl));
/*        RetrofitManager.builder().getNewsDetail(mNews.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                })
                .subscribe(new Action1<NewsDetail>() {
                    @Override
                    public void call(NewsDetail newsDetail) {
                        hideProgress();
                        L.object(newsDetail);
                        if (newsDetail == null) {
                            mTvLoadEmpty.setVisibility(View.VISIBLE);
                        } else {
                            Glide.with(getActivity())
                                    .load(newsDetail.getImage())
                                    .into(mIvHeader);
                            mTvTitle.setText(newsDetail.getTitle());
                            mTvSource.setText(newsDetail.getImage_source());
                            String htmlData = HtmlUtil.createHtmlData(newsDetail);
                            mWvNews.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                            mTvLoadEmpty.setVisibility(View.GONE);
                        }
                        mTvLoadError.setVisibility(View.GONE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideProgress();
                        L.e(throwable,"Load news detail error");
                        mTvLoadError.setVisibility(View.VISIBLE);
                        mTvLoadEmpty.setVisibility(View.GONE);
                    }
                });*/
    }


    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
