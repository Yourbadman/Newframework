package com.jsonwong.newframework.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors：Administrator on 2016/4/7 21:46
 */
public class T1348647909107 {

    /**
     * postid : BK30HKCS00963VRO
     * url_3w : http://help.3g.163.com/16/0407/20/BK30HKCS00963VRO.html
     * votecount : 89
     * replyCount : 359
     * skipID : S1451880983492
     * ltitle : 习近平首论林业与全面小康之关系
     * digest : 发展林业是全面建成小康社会的重要内容，可加快其进程。
     * skipType : special
     * url : http://3g.163.com/ntes/16/0407/20/BK30HKCS00963VRO.html
     * specialID : S1451880983492
     * docid : BK30HKCS00963VRO
     * title : 习近平论林业与全面小康关系
     * source : 学习中国
     * priority : 220
     * lmodify : 2016-04-07 20:55:09
     * boardid : 3g_bbs
     * subtitle :
     * imgsrc : http://img3.cache.netease.com/3g/2016/4/7/2016040720501728125.png
     * ptime : 2016-04-07 20:46:39
     */

    private String postid;
    private String url_3w;
    private int votecount;
    private int replyCount;
    private String skipID;
    private String ltitle;
    private String digest;
    private String skipType;
    private String url;
    private String specialID;
    private String docid;
    private String title;
    private String source;
    private int priority;
    private String lmodify;
    private String boardid;
    private String subtitle;
    private String imgsrc;
    private String ptime;

    public static T1348647909107 objectFromData(String str) {

        return new Gson().fromJson(str, T1348647909107.class);
    }

    public static T1348647909107 objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), T1348647909107.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<T1348647909107> arrayT1348647909107FromData(String str) {

        Type listType = new TypeToken<ArrayList<T1348647909107>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<T1348647909107> arrayT1348647909107FromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<T1348647909107>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
