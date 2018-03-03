package com.able.rx.tools.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.able.rx.utils.JsonUtils;

/**
 * Created by ZhangJinyu on 2018/2/26.
 */

public class BusEvent<T> implements Parcelable {
    private String tag;
    private T data;

    public BusEvent(String tag) {
        this.tag = tag;
    }

    public BusEvent(Class tagClazz) {
        this.tag = tagClazz.getName();
    }

    public String getTag() {
        return tag;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeString(data.getClass().getName());
        dest.writeString(JsonUtils.getInstance().toJson(data));
    }

    protected BusEvent(Parcel in) {
        this.tag = in.readString();
        String dataName = in.readString();
        String dataJson = in.readString();
        if (TextUtils.isEmpty(dataJson)) {
            this.data = null;
        } else {
            try {
                this.data = (T) JsonUtils.getInstance().format(dataJson, Class.forName(dataName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static final Parcelable.Creator<BusEvent> CREATOR = new Parcelable.Creator<BusEvent>() {
        @Override
        public BusEvent createFromParcel(Parcel source) {
            return new BusEvent(source);
        }

        @Override
        public BusEvent[] newArray(int size) {
            return new BusEvent[size];
        }
    };
}
