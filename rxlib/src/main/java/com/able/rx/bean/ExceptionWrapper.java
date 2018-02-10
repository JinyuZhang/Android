package com.able.rx.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by guanlijie on 2018/2/10.
 */

public class ExceptionWrapper implements Parcelable {
    private String titleMsg;
    private String detailMsg;

    public ExceptionWrapper() {
    }

    public void setWrapperData(String titleMsg, String detailMsg) {
        this.titleMsg = titleMsg;
        this.detailMsg = detailMsg;
    }

    public String getTitleMsg() {
        return titleMsg;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titleMsg);
        dest.writeString(this.detailMsg);
    }

    protected ExceptionWrapper(Parcel in) {
        this.titleMsg = in.readString();
        this.detailMsg = in.readString();
    }

    public static final Parcelable.Creator<ExceptionWrapper> CREATOR = new Parcelable.Creator<ExceptionWrapper>() {
        @Override
        public ExceptionWrapper createFromParcel(Parcel source) {
            return new ExceptionWrapper(source);
        }

        @Override
        public ExceptionWrapper[] newArray(int size) {
            return new ExceptionWrapper[size];
        }
    };
}
