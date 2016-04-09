package com.jsonwong.newframework.api.remote;

import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.api.ApiHttpClient;
import com.jsonwong.newframework.util.Constants;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class OSChinaApi {


    /**
     * 获取新闻列表
     *
     * @param page    第几页
     * @param handler
     */
    public static void getNewsList(String url, int page,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        // params.put("catalog", catalog);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
//        if (catalog == Constants.CATALOG_WEEK) {
//            params.put("show", "week");
//        } else if (catalog == Constants.CATALOG_MONTH) {
//            params.put("show", "month");
//        }
        ApiHttpClient.get(url, handler);
    }


}
