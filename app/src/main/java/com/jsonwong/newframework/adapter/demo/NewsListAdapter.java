package com.jsonwong.newframework.adapter.demo;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.modle.NewsListBean;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.base.ListBaseAdapter;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.util.DraweeUtils;
import com.jsonwong.newframework.util.StringUtils;
import com.jsonwong.newframework.util.ThemeSwitchUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsListAdapter extends ListBaseAdapter<NewsListBean> {

    @SuppressLint("InflateParams")
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        NewsListBean news = mDatas.get(position);

        //右侧图片，左边标题，内容
        if (news.getImgextra() == null) {
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

            if (AppContext.isOnReadedPostList(Constants.PREF_READED_NEWS_LIST,
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
        } else if (news.getImgextra().size() > 0) {
            ViewHoldePhoto vh = null;
            if (convertView == null || convertView.getTag() == null) {
                convertView = getLayoutInflater(parent.getContext()).inflate(
                        R.layout.list_cell_news, null);
                vh = new ViewHoldePhoto(((ViewStub) convertView.findViewById(R.id.stub_list_cell_news_photo)).inflate());
                convertView.setTag(R.id.stub_list_cell_news_photo, vh);
            } else {
                vh = (ViewHoldePhoto) convertView.getTag(R.id.stub_list_cell_news_photo);
            }


            //设置显示图片
            List<NewsListBean.ImgextraBean> images = news.getImgextra();
            if (images.size() == 1) {

                setLayotuParams(vh.photo_1, 0.5f);
                DraweeUtils.setImageUrl(vh.photo_1, news.getImgsrc());

                setLayotuParams(vh.photo_2, 0.5f);
                DraweeUtils.setImageUrl(vh.photo_2, images.get(0).getImgsrc());
            }
            if (images.size() == 2) {

                setLayotuParams(vh.photo_1, 0.33f);
                DraweeUtils.setImageUrl(vh.photo_1, news.getImgsrc());

                setLayotuParams(vh.photo_2, 0.33f);
                DraweeUtils.setImageUrl(vh.photo_2, images.get(0).getImgsrc());

                setLayotuParams(vh.photo_3, 0.33f);
                DraweeUtils.setImageUrl(vh.photo_3, images.get(1).getImgsrc());
            }
//            if (images.size() == 3) {
//
//                setLayotuParams(vh.photo_1, 0.33f);
//                DraweeUtils.setImageUrl(vh.photo_1, images.get(0).getImgsrc());
//
//                setLayotuParams(vh.photo_2, 0.33f);
//                DraweeUtils.setImageUrl(vh.photo_2, images.get(1).getImgsrc());
//
//                setLayotuParams(vh.photo_3, 0.33f);
//                DraweeUtils.setImageUrl(vh.photo_3, images.get(2).getImgsrc());
//            }


            vh.title.setText(news.getTitle());
            vh.comment_count.setText(news.getReplyCount() + "");
            if (AppContext.isOnReadedPostList(Constants.PREF_READED_NEWS_LIST,
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

    private void setLayotuParams(SimpleDraweeView view, float weight) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        param.setMargins(2, 2, 2, 2);
        view.setLayoutParams(param);
        view.setVisibility(View.VISIBLE);
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
