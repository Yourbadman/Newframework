
package com.jsonwong.newframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class NewModle implements Parcelable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * docid
     */
    private String docid;
    /**
     * 标题
     */
    private String title;
    /**
     * 小内容
     */
    private String digest;
    /**
     * 图片地址
     */
    private String imgsrc;
    /**
     * 来源
     */
    private String source;
    /**
     * 时间
     */
    private String ptime;
    /**
     * TAG
     */
    private String tag;
    /**
     * 列表
     */
    private ImagesModle imagesModle;
    /**
     * 评论条数
     */
    private String replyCount;
    /**
     * 3g_URL
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReplyCount() {
        return this.replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ImagesModle getImagesModle() {
        return imagesModle;
    }

    public void setImagesModle(ImagesModle imagesModle) {
        this.imagesModle = imagesModle;
    }

    /**
     * 头部列表
     */
    private List<ImagesModle> imgHeadLists;

    public List<ImagesModle> getImgHeadLists() {
        return imgHeadLists;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setImgHeadLists(List<ImagesModle> imgHeadLists) {
        this.imgHeadLists = imgHeadLists;
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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

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
        dest.writeString(this.docid);
        dest.writeString(this.title);
        dest.writeString(this.digest);
        dest.writeString(this.imgsrc);
        dest.writeString(this.source);
        dest.writeString(this.ptime);
        dest.writeString(this.tag);
        dest.writeParcelable(this.imagesModle, flags);
        dest.writeString(this.replyCount);
        dest.writeString(this.url);
        dest.writeList(this.imgHeadLists);
    }

    public NewModle() {
    }

    protected NewModle(Parcel in) {
        this.docid = in.readString();
        this.title = in.readString();
        this.digest = in.readString();
        this.imgsrc = in.readString();
        this.source = in.readString();
        this.ptime = in.readString();
        this.tag = in.readString();
        this.imagesModle = in.readParcelable(ImagesModle.class.getClassLoader());
        this.replyCount = in.readString();
        this.url = in.readString();
        this.imgHeadLists = new ArrayList<ImagesModle>();
        in.readList(this.imgHeadLists, ImagesModle.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewModle> CREATOR = new Parcelable.Creator<NewModle>() {
        @Override
        public NewModle createFromParcel(Parcel source) {
            return new NewModle(source);
        }

        @Override
        public NewModle[] newArray(int size) {
            return new NewModle[size];
        }
    };
}
