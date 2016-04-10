
package com.jsonwong.modle;

import android.os.Parcel;
import android.os.Parcelable;

import com.jsonwong.modle.base.BaseModle;

import java.util.List;

public class ImagesModle extends BaseModle implements Parcelable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * docid
     */
    private String docid;
    /**
     * title
     */
    private String title;
    /**
     * 图片集
     */
    private List<String> imgList;

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

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.docid);
        dest.writeString(this.title);
        dest.writeStringList(this.imgList);
    }

    public ImagesModle() {
    }

    protected ImagesModle(Parcel in) {
        this.docid = in.readString();
        this.title = in.readString();
        this.imgList = in.createStringArrayList();
    }

    public static final Creator<ImagesModle> CREATOR = new Creator<ImagesModle>() {
        @Override
        public ImagesModle createFromParcel(Parcel source) {
            return new ImagesModle(source);
        }

        @Override
        public ImagesModle[] newArray(int size) {
            return new ImagesModle[size];
        }
    };
}
