package com.jsonwong.newframework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jsonwong.modle.NewsListBean;
import com.jsonwong.mvp.adapter.BasePullUpRecyclerAdapter;
import com.jsonwong.mvp.adapter.RecyclerHolder;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.util.DraweeUtils;
import com.jsonwong.newframework.util.StringUtils;
import com.jsonwong.newframework.util.ThemeSwitchUtils;

import java.util.Collection;
import java.util.List;

public class NewsListAdapter extends BasePullUpRecyclerAdapter<NewsListBean> {

    public NewsListAdapter(RecyclerView v, Collection<NewsListBean> datas, int... itemLayoutId) {
        super(v, datas, itemLayoutId);
    }

    @Override
    public int getCustomItemLayoutByPosition(int position) {
        NewsListBean news = super.realDatas.get(position);
        if (super.mItemLayoutId.length == 1)
            return 0;
        if (news.getImgextra() == null) {
            return 0;//R.id.stub_list_cell_news_base
        } else {
            return 1;//R.id.stub_list_cell_news_photo
        }
    }


    @Override
    public void convert(RecyclerHolder holder, NewsListBean news, int position) {
        holder.setText(R.id.tv_title, news.getTitle());
        if (AppContext.isOnReadedPostList(Constants.PREF_READED_NEWS_LIST,
                news.getDocid() + "")) {
            holder.setTextColor(R.id.tv_title, holder.itemView.getContext().getResources()
                    .getColor(ThemeSwitchUtils.getTitleReadedColor()));
        } else {
            holder.setTextColor(R.id.tv_title, holder.itemView.getContext().getResources()
                    .getColor(ThemeSwitchUtils.getTitleUnReadedColor()));
        }
        holder.setText(R.id.tv_source, news.getSource());
//
        holder.setText(R.id.tv_time, StringUtils.friendly_time(news.getPtime()));
        holder.setText(R.id.tv_comment_count, news.getReplyCount() + "");

        if (news.getImgextra() == null) {

            //tv_description
            String description = news.getDigest();
            holder.setVisibility(R.id.tv_description, View.GONE);
            if (description != null && !StringUtils.isEmpty(description)) {
                holder.setVisibility(R.id.tv_description, View.VISIBLE);
                holder.setText(R.id.tv_description, description.trim());
            }

            if (news.getImgsrc() != null) {
                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.thumbnailsView), news.getImgsrc());
            }
            //------------------------ 三张图片布局-------------------------------
        } else if (news.getImgextra().size() > 0) {
            //设置显示图片
            List<NewsListBean.ImgextraBean> images = news.getImgextra();
            if (images.size() == 1) {
                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.photo_1), news.getImgsrc());
                setLayotuParams((SimpleDraweeView) holder.getView(R.id.photo_1), 0.5f);

                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.photo_2), news.getImgsrc());
                setLayotuParams((SimpleDraweeView) holder.getView(R.id.photo_2), 0.5f);

            }
            if (images.size() == 2) {

                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.photo_1), news.getImgsrc());
                setLayotuParams((SimpleDraweeView) holder.getView(R.id.photo_1), 0.33f);

                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.photo_2), news.getImgsrc());
                setLayotuParams((SimpleDraweeView) holder.getView(R.id.photo_2), 0.33f);

                DraweeUtils.setImageUrl((SimpleDraweeView) holder.getView(R.id.photo_3), news.getImgsrc());
                setLayotuParams((SimpleDraweeView) holder.getView(R.id.photo_3), 0.33f);

            }
        }
    }

    private void setLayotuParams(SimpleDraweeView view, float weight) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        param.setMargins(2, 2, 2, 2);
        view.setLayoutParams(param);
        view.setVisibility(View.VISIBLE);
    }

}
