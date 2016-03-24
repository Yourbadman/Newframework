package com.jsonwong.newframework.api.remote;

import com.jsonwong.newframework.api.ApiHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class OSChinaApi {



    /**
     * 获取新闻列表
     *
     * @param catalog
     *            类别 （1，2，3）
     * @param page
     *            第几页
     * @param handler
     */
    public static void getNewsList(String url,int catalog, int page,
            AsyncHttpResponseHandler handler) {
        /* RequestParams params = new RequestParams();
      /*  params.put("catalog", catalog);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        if (catalog == NewsList.CATALOG_WEEK) {
            params.put("show", "week");
        } else if (catalog == NewsList.CATALOG_MONTH) {
            params.put("show", "month");
        }*/
        ApiHttpClient.get(url, handler);
    }


}
