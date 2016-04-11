package com.jsonwong.newframework.db.service;

import android.content.Context;

import com.jsonwong.greendao.ChannelItem;
import com.jsonwong.greendao.ChannelItemDao;
import com.jsonwong.greendao.DaoSession;
import com.jsonwong.newframework.base.BaseApplication;
import com.jsonwong.newframework.api.http.Url;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by wrd66 on 2016/3/16.
 */
public class ChannelManageService {
    private static final String TAG = ChannelManageService.class.getSimpleName();
    private static ChannelManageService instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private ChannelItem channelItem;
    /**
     * 默认的用户选择频道列表
     */
    public static List<ChannelItem> defaultUserChannels;
    /**
     * 默认的其他频道列表
     */
    public static List<ChannelItem> defaultOtherChannels;
    private ChannelItemDao channelDao;
    /**
     * 判断数据库中是否存在用户数据
     */
    private boolean userExist = false;

    private ChannelManageService() {
    }

    public static ChannelManageService getInstance(Context context) {
        if (instance == null) {
            instance = new ChannelManageService();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = BaseApplication.getDaoSession(context);
            instance.channelDao = instance.mDaoSession.getChannelItemDao();
        }
        return instance;
    }

    static {
        defaultUserChannels = new ArrayList<ChannelItem>();
        defaultOtherChannels = new ArrayList<ChannelItem>();
        defaultUserChannels.add(new ChannelItem(1, "头条", 1, 1, "T1348647909107", Url.UrlType.HeadUrl.getId()));
        defaultUserChannels.add(new ChannelItem(2, "足球", 2, 1, "T1399700447917", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(3, "娱乐", 3, 1, "T1348648517839", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(4, "体育", 4, 1, "T1348649079062", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(5, "财经", 5, 1, "T1348648756099", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(6, "科技", 6, 1, "T1348649580692", Url.UrlType.CommonUrl.getId()));
        // defaultUserChannels.add(new ChannelItem(7, "图片", 1, 0));
        defaultOtherChannels.add(new ChannelItem(7, "CBA", 1, 0, "T1348649475931", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(8, "笑话", 2, 0, "T1350383429665", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(9, "汽车", 3, 0, "T1348654060988", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(10, "时尚", 4, 0, "T1348650593803", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(11, "北京", 5, 0, "5YyX5Lqs", Url.UrlType.HousrUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(12, "军事", 6, 0, "T1348648141035", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(13, "房产", 7, 0, "5YyX5Lqs", Url.UrlType.HousrUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(14, "游戏", 8, 0, "T1348654151579", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(15, "精选", 9, 0, "T1370583240249", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(16, "电台", 10, 0, "T1379038288239", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(17, "情感", 11, 0, "T1348650839000", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(18, "电影", 12, 0, "T1348648650048", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(19, "NBA", 13, 0, "T1348649145984", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(20, "数码", 14, 0, "T1348649776727", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(21, "移动", 15, 0, "T1351233117091", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(22, "彩票", 16, 0, "T1356600029035", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(23, "教育", 17, 0, "T1348654225495", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(24, "论坛", 18, 0, "T1349837670307", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(25, "旅游", 19, 0, "T1348654204705", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(26, "手机", 20, 0, "T1348649654285", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(27, "博客", 21, 0, "T1349837698345", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(28, "社会", 22, 0, "T1348648037603", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(29, "家居", 23, 0, "T1348654105308", Url.UrlType.CommonUrl.getId()));
        defaultOtherChannels.add(new ChannelItem(30, "暴雪", 24, 0, "T1397016069906", Url.UrlType.CommonUrl.getId()));
        defaultUserChannels.add(new ChannelItem(31, "亲子", 25, 0, "T1397116135282", Url.UrlType.CommonUrl.getId()));
    }

    /**
     * 初始化数据
     */
    public void initData() {
        deleteAllChannel();
        saveUserChannel(defaultUserChannels);
        saveOtherChannel(defaultOtherChannels);
    }

    public void deleteAllChannel() {
        channelDao.deleteAll();
    }


    /**
     * 获取用户频道
     *
     * @return
     */
    public List<ChannelItem> getUserChannel() {
        QueryBuilder queryBuilder = channelDao.queryBuilder();
        queryBuilder.where(ChannelItemDao.Properties.Selected.eq("1")).orderAsc(ChannelItemDao.Properties.OrderId);
        List<ChannelItem> list = queryBuilder.list();
        return list.size() == 0 ? new ArrayList<ChannelItem>() : list;
    }


    public List<ChannelItem> getOtherChannel() {
        QueryBuilder queryBuilder = channelDao.queryBuilder();
        queryBuilder.where(ChannelItemDao.Properties.Selected.eq("0"));
        List<ChannelItem> list = queryBuilder.list();
        return list.size() == 0 ? new ArrayList<ChannelItem>() : list;
    }


    public void saveUserChannel(final List<ChannelItem> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        channelDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < userList.size(); i++) {
                    ChannelItem channelItem = userList.get(i);
                    channelDao.insertOrReplace(channelItem);
                }
            }
        });
    }


    public void saveOtherChannel(List<ChannelItem> otherList) {
        saveUserChannel(otherList);
    }


    public void updateChannel(ChannelItem channelItem, String selected) {
        channelItem.setSelected(Integer.parseInt(selected));
        channelDao.update(channelItem);
    }
}
