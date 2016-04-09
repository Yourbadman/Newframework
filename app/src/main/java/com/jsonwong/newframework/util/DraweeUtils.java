package com.jsonwong.newframework.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.bean.NewsListBean;

/**
 * Fresco 帮助Utils
 * <p/>
 * Authors：Administrator on 2016/3/23 14:21
 */
public class DraweeUtils {

    /**
     * 设置图片的圆角效果
     *
     * @param simpleDraweeView
     * @param rate
     */
    public static void setDraweeRound(SimpleDraweeView simpleDraweeView, float rate) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(R.color.red, rate);
        roundingParams.setRoundAsCircle(true);
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
    }

    /**
     * Fresco supports the streaming of progressive JPEG images over the network.
     * jpeg图片格式的渐变显示----模糊到清晰显示
     *
     * @param simpleDraweeView
     * @param url
     * @return
     */
    public static DraweeController getDraweeController(SimpleDraweeView simpleDraweeView, String url) {
        //参考api  http://frescolib.org/docs/progressive-jpegs.html#_
        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                        .setProgressiveRenderingEnabled(true)
                        .build())
                .setOldController(simpleDraweeView.getController())
                .build();
    }

    public static void setImageUrl(SimpleDraweeView photo_2, String url) {

        if (StringUtils.isEmpty(url))
            return;
        Uri uri = Uri.parse(url);
        if (uri == null)
            return;
        photo_2.setImageURI(uri);


    }
}
