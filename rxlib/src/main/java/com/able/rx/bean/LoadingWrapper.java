package com.able.rx.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class LoadingWrapper implements Parcelable {
    private LoadingType loadingType;
    private String tag;
    private String titleText;
    private String detailText;

    public LoadingWrapper(String tag, String titleText, String detailText) {
        this.loadingType = LoadingType.CONTENT;
        this.tag = tag;
        this.titleText = titleText;
        this.detailText = detailText;
    }

    public LoadingWrapper(LoadingType loadingType, String tag, String titleText) {
        this.loadingType = loadingType;
        this.tag = tag;
        this.titleText = titleText;
        this.detailText = null;
    }

    public LoadingType getLoadingType() {
        return loadingType;
    }

    public String getTag() {
        return tag;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getDetailText() {
        return detailText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoadingWrapper loadingWrapper = (LoadingWrapper) o;

        if (loadingType != loadingWrapper.loadingType) return false;
        return tag != null ? tag.equals(loadingWrapper.tag) : loadingWrapper.tag == null;
    }

    @Override
    public int hashCode() {
        int result = loadingType != null ? loadingType.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.loadingType == null ? -1 : this.loadingType.ordinal());
        dest.writeString(this.tag);
        dest.writeString(this.titleText);
        dest.writeString(this.detailText);
    }

    protected LoadingWrapper(Parcel in) {
        int tmpLoadingType = in.readInt();
        this.loadingType = tmpLoadingType == -1 ? null : LoadingType.values()[tmpLoadingType];
        this.tag = in.readString();
        this.titleText = in.readString();
        this.detailText = in.readString();
    }

    public static final Creator<LoadingWrapper> CREATOR = new Creator<LoadingWrapper>() {
        @Override
        public LoadingWrapper createFromParcel(Parcel source) {
            return new LoadingWrapper(source);
        }

        @Override
        public LoadingWrapper[] newArray(int size) {
            return new LoadingWrapper[size];
        }
    };
}
