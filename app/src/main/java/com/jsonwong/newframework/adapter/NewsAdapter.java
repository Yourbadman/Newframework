package com.jsonwong.newframework.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.base.ListBaseAdapter;
import com.jsonwong.newframework.bean.NewModle;
import com.jsonwong.newframework.bean.NewsList;
import com.jsonwong.newframework.util.StringUtils;
import com.jsonwong.newframework.util.ThemeSwitchUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsAdapter extends ListBaseAdapter<NewModle> {

    @SuppressLint("InflateParams")
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        NewModle news = mDatas.get(position);

        //右侧图片，左边标题，内容
        if (news.getImagesModle() == null || news.getImagesModle().getImgList() == null) {
            ViewHoldeBase vh = null;
            if (convertView == null || convertView.getTag() == null) {
                convertView = getLayoutInflater(parent.getContext()).inflate(
                        R.layout.list_cell_news, null);
                vh = new ViewHoldeBase(((ViewStub) convertView.findViewById(R.id.stub_list_cell_news_base)).inflate());
                convertView.setTag(R.id.stub_list_cell_news_base, vh);
            } else {
                vh = (ViewHoldeBase) convertView.getTag(R.id.stub_list_cell_news_base);
            }

            vh.title.setText(news.getTitle());

            if (AppContext.isOnReadedPostList(NewsList.PREF_READED_NEWS_LIST,
                    news.getDocid() + "")) {
                vh.title.setTextColor(parent.getContext().getResources()
                        .getColor(ThemeSwitchUtils.getTitleReadedColor()));
            } else {
                vh.title.setTextColor(parent.getContext().getResources()
                        .getColor(ThemeSwitchUtils.getTitleUnReadedColor()));
            }
            String description = news.getDigest();
            vh.description.setVisibility(View.GONE);
            if (description != null && !StringUtils.isEmpty(description)) {
                vh.description.setVisibility(View.VISIBLE);
                vh.description.setText(description.trim());
            }

            vh.source.setText(news.getSource());

            vh.time.setText(StringUtils.friendly_time(news.getPtime()));
            vh.comment_count.setText(news.getReplyCount() + "");
            if (news.getImgsrc() != null) {
                Uri uri = Uri.parse(news.getImgsrc());
                vh.thumbnailsView.setImageURI(uri);
            }
            //------------------------ 三张图片布局-------------------------------
        }

        else if (news.getImagesModle().getImgList().size() == 3) {
            ViewHoldePhoto vh = null;
            if (convertView == null || convertView.getTag() == null) {
                convertView = getLayoutInflater(parent.getContext()).inflate(
                        R.layout.list_cell_news, null);
                vh = new ViewHoldePhoto(((ViewStub) convertView.findViewById(R.id.stub_list_cell_news_photo)).inflate());
                convertView.setTag(R.id.stub_list_cell_news_photo, vh);
            } else {
                vh = (ViewHoldePhoto) convertView.getTag(R.id.stub_list_cell_news_photo);
            }
            List<String> images = news.getImagesModle().getImgList();
            if (images.get(0) != null) {
                vh.photo_1.setImageURI(Uri.parse(images.get(0)));
            }
            if (images.get(1) != null) {
                vh.photo_2.setImageURI(Uri.parse(images.get(1)));
            }
            if (images.get(2) != null) {
                vh.photo_3.setImageURI(Uri.parse(images.get(2)));
            }
            vh.title.setText(news.getTitle());
            vh.comment_count.setText(news.getReplyCount() + "");
            if (AppContext.isOnReadedPostList(NewsList.PREF_READED_NEWS_LIST,
                    news.getDocid() + "")) {
                vh.title.setTextColor(parent.getContext().getResources()
                        .getColor(ThemeSwitchUtils.getTitleReadedColor()));
            } else {
                vh.title.setTextColor(parent.getContext().getResources()
                        .getColor(ThemeSwitchUtils.getTitleUnReadedColor()));
            }

            vh.source.setText(news.getSource());

            vh.time.setText(StringUtils.friendly_time(news.getPtime()));
            vh.comment_count.setText(news.getReplyCount() + "");
            //-------------------------------------------------------------------

        }
        return convertView;
    }

    static class ViewHoldePhoto {
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.photo_1)
        SimpleDraweeView photo_1;
        @InjectView(R.id.photo_2)
        SimpleDraweeView photo_2;
        @InjectView(R.id.photo_3)
        SimpleDraweeView photo_3;
        @InjectView(R.id.tv_source)
        TextView source;
        @InjectView(R.id.tv_time)
        TextView time;
        @InjectView(R.id.tv_comment_count)
        TextView comment_count;

        public ViewHoldePhoto(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class ViewHoldeBase {
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.tv_description)
        TextView description;
        @InjectView(R.id.tv_source)
        TextView source;
        @InjectView(R.id.tv_time)
        TextView time;
        @InjectView(R.id.tv_comment_count)
        TextView comment_count;
        //缩略图
        @InjectView(R.id.thumbnailsView)
        SimpleDraweeView thumbnailsView;

        public ViewHoldeBase(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
