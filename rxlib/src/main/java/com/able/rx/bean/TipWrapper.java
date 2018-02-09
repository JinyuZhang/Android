package com.able.rx.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class TipWrapper implements Parcelable {
    private String tag;
    private TipType tipType;
    private String titleText;
    private String buttonText;
    private String msg;

    public TipWrapper(String tag, TipType tipType) {
        this.tag = tag;
        this.tipType = tipType;
    }

    public String getTag() {
        return tag;
    }

    public TipType getTipType() {
        return tipType;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipWrapper that = (TipWrapper) o;

        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
        return tipType == that.tipType;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (tipType != null ? tipType.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeInt(this.tipType == null ? -1 : this.tipType.ordinal());
        dest.writeString(this.titleText);
        dest.writeString(this.buttonText);
        dest.writeString(this.msg);
    }

    protected TipWrapper(Parcel in) {
        this.tag = in.readString();
        int tmpTipType = in.readInt();
        this.tipType = tmpTipType == -1 ? null : TipType.values()[tmpTipType];
        this.titleText = in.readString();
        this.buttonText = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<TipWrapper> CREATOR = new Creator<TipWrapper>() {
        @Override
        public TipWrapper createFromParcel(Parcel source) {
            return new TipWrapper(source);
        }

        @Override
        public TipWrapper[] newArray(int size) {
            return new TipWrapper[size];
        }
    };
}
