package com.jsonwong.newframework.fragment;

import com.jsonwong.newframework.base.CommonDetailFragment;
import com.jsonwong.newframework.bean.NewModle;
import com.jsonwong.newframework.util.StringUtils;
import com.jsonwong.newframework.util.ThemeSwitchUtils;
import com.jsonwong.newframework.util.UIHelper;

import java.io.InputStream;

/**
 * Created by 火蚁 on 15/5/25.
 */
public class NewsDetailFragment extends CommonDetailFragment<NewModle> {

    @Override
    protected String getCacheKey() {
        return "news_" + mId;
    }

    @Override
    protected void sendRequestDataForNet() {
        //OSChinaApi.getNewsDetail(mId, mDetailHeandler);
    }

    @Override
    protected NewModle parseData(InputStream is) {
        return null;
        // return XmlUtils.toBean(NewsDetail.class, is).getNews();
    }

    @Override
    protected String getWebViewBody(NewModle detail) {
        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", mDetail.getTitle()));
        // 添加作者和时间
        String time = StringUtils.friendly_time(mDetail.getPtime());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", mDetail.getSource(), mDetail.getSource());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        // body.append(UIHelper.setHtmlCotentSupportImagePreview(mDetail.getBody()));


        // 相关新闻
       /* if (mDetail != null && mDetail.getRelatives() != null
                && mDetail.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : mDetail.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }*/
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }


}
