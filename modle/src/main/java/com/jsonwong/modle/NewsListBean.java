package com.jsonwong.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表实体
 * Authors：Administrator on 2016/4/8 13:06
 */
public class NewsListBean implements Parcelable {


    /**
     * postid : BK4DDNII00234KO7
     * url_3w : http://help.3g.163.com/16/0408/09/BK4DDNII00234KO7.html
     * votecount : 0
     * replyCount : 62
     * skipID : S1451880983492
     * ltitle : 习近平:全面从严治党首先要尊崇党章
     * digest : 各级纪委要全面履行党章赋予的职责，带头尊崇党章。
     * skipType : special
     * url : http://3g.163.com/ntes/16/0408/09/BK4DDNII00234KO7.html
     * specialID : S1451880983492
     * docid : BK4DDNII00234KO7
     * title : 习近平：从严治党要尊崇党章
     * source : 新华网$
     * priority : 240
     * lmodify : 2016-04-08 09:50:57
     * boardid : news_gov_bbs
     * subtitle :
     * imgsrc : http://img2.cache.netease.com/3g/2016/4/8/20160408095428b230e.jpg
     * ptime : 2016-04-08 09:50:57
     * TAG : 独家
     * TAGs : 独家
     * imgextra : [{"imgsrc":"http://img2.cache.netease.com/3g/2016/4/8/20160408042707eed6f.jpg"},{"imgsrc":"http://img3.cache.netease.com/3g/2016/4/8/2016040804271094066.jpg"}]
     * photosetID : 54GI0096|91234
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
    private String TAG;
    private String TAGs;
    private String photosetID;
    /**
     * imgsrc : http://img2.cache.netease.com/3g/2016/4/8/20160408042707eed6f.jpg
     */

    private List<ImgextraBean> imgextra;

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

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getTAGs() {
        return TAGs;
    }

    public void setTAGs(String TAGs) {
        this.TAGs = TAGs;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class ImgextraBean implements Parcelable {
        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.imgsrc);
        }

        public ImgextraBean() {
        }

        protected ImgextraBean(Parcel in) {
            this.imgsrc = in.readString();
        }

        public static final Creator<ImgextraBean> CREATOR = new Creator<ImgextraBean>() {
            @Override
            public ImgextraBean createFromParcel(Parcel source) {
                return new ImgextraBean(source);
            }

            @Override
            public ImgextraBean[] newArray(int size) {
                return new ImgextraBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.postid);
        dest.writeString(this.url_3w);
        dest.writeInt(this.votecount);
        dest.writeInt(this.replyCount);
        dest.writeString(this.skipID);
        dest.writeString(this.ltitle);
        dest.writeString(this.digest);
        dest.writeString(this.skipType);
        dest.writeString(this.url);
        dest.writeString(this.specialID);
        dest.writeString(this.docid);
        dest.writeString(this.title);
        dest.writeString(this.source);
        dest.writeString(this.subtitle);
        dest.writeString(this.imgsrc);
        dest.writeString(this.ptime);
        dest.writeString(this.photosetID);
        dest.writeList(this.imgextra);
    }

    public NewsListBean() {
    }

    protected NewsListBean(Parcel in) {
        this.postid = in.readString();
        this.url_3w = in.readString();
        this.votecount = in.readInt();
        this.replyCount = in.readInt();
        this.skipID = in.readString();
        this.ltitle = in.readString();
        this.digest = in.readString();
        this.skipType = in.readString();
        this.url = in.readString();
        this.specialID = in.readString();
        this.docid = in.readString();
        this.title = in.readString();
        this.source = in.readString();
        this.subtitle = in.readString();
        this.imgsrc = in.readString();
        this.ptime = in.readString();
        this.photosetID = in.readString();
        this.imgextra = new ArrayList<ImgextraBean>();
        in.readList(this.imgextra, ImgextraBean.class.getClassLoader());
    }

    public static final Creator<NewsListBean> CREATOR = new Creator<NewsListBean>() {
        @Override
        public NewsListBean createFromParcel(Parcel source) {
            return new NewsListBean(source);
        }

        @Override
        public NewsListBean[] newArray(int size) {
            return new NewsListBean[size];
        }
    };
}
