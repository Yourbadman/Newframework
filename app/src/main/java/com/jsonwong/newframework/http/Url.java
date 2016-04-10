
package com.jsonwong.newframework.http;


import com.jsonwong.greendao.ChannelItem;

public class Url {

    public enum UrlType {

        HeadUrl(1, "nc/article/headline/"),
        HousrUrl(2, "nc/article/house/"),
        LocalUrl(3, "nc/article/local/"),
        CommonUrl(4, "nc/article/list/");

        private UrlType(int i, String url) {
            this.id = i;
            this.url = url;

        }

        private Integer id;
        private String url;


        public static String getUrlById(int id) {
            switch (id) {
                case 1:
                    return HeadUrl.getUrl();
                case 2:
                    return HousrUrl.getUrl();
                case 3:
                    return LocalUrl.getUrl();
                case 4:
                    return CommonUrl.getUrl();
                default:
                    return HeadUrl.getUrl();
            }

        }

        public Integer getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static final String host = "http://c.m.163.com/";
    public static final String endUrl = "-20.html";
    public static final String endDetailUrl = "/full.html";
    // 新闻详情
    public static final String NewDetail = host + "nc/article/";


    // 图集
    public static final String TuJi = host + "photo/api/morelist/0096/4GJ60096/";// 42358.json
    // 图集end
    public static final String TuJiEnd = ".json";
    // 热点42577
    public static final String TuPianReDian = host + "photo/api/morelist/0096/54GI0096/";
    // 独家42010
    public static final String TuPianDuJia = host + "photo/api/morelist/0096/54GJ0096/";
    // 明星 42599.json
    public static final String TuPianMingXing = host + "photo/api/morelist/0096/54GK0096/";
    // 明星 42262.json
    public static final String TuPianTiTan = host + "photo/api/morelist/0096/54GM0096/";
    // 美图 39683.json
    public static final String TuPianMeiTu = host + "photo/api/morelist/0096/54GN0096/";

    // 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public static final String Video = host + "nc/video/list/";
    public static final String VideoCenter = "/n/";
    public static final String videoEndUrl = "-10.html";
    // 热点视频
    public static final String VideoReDianId = "V9LG4B3A0";
    // 娱乐视频
    public static final String VideoYuLeId = "V9LG4CHOR";
    // 搞笑视频
    public static final String VideoGaoXiaoId = "V9LG4E6VR";
    // 精品视频
    public static final String VideoJingPinId = "00850FRB";

    // 天气预报url
    public static final String WeatherHost = "http://wthrcdn.etouch.cn/weather_mini?city=";
    // http://v.juhe.cn/weather/index?cityname=
    //

    public static final String WeatherKey = "&key=1734f933d24634331a24aaadc1cb088f";

    /**
     * 新浪图片新闻
     */
    // 精选列表
    public static final String JINGXUAN_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 图片详情
    public static final String JINGXUANDETAIL_ID = "http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";
    // 趣图列表
    public static final String QUTU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 美图列表
    public static final String MEITU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 故事列表
    public static final String GUSHI_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";

    public static String getNewUrl(ChannelItem channleItem, String index) {
        return Url.host + UrlType.getUrlById(channleItem.getUrlType()) + channleItem.getChannelId() + "/" + index + Url.endUrl;

    }
}
