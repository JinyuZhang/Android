package com.able.android.bean.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.able.rx.utils.JsonUtils;

/**
 * Created by guanlijie on 2018/2/9.
 */

public class AppBaseResponse<T> implements Parcelable {
    private int status;
    private String msg;
    private String serverTime;
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getServerTime() {
        return serverTime;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return status == 200;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.msg);
        dest.writeString(this.serverTime);
        dest.writeString(data.getClass().getName());
        dest.writeString(JsonUtils.getInstance().toJson(data));
    }

    public AppBaseResponse() {
    }

    protected AppBaseResponse(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
        this.serverTime = in.readString();
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

    public static final Parcelable.Creator<AppBaseResponse> CREATOR = new Parcelable.Creator<AppBaseResponse>() {
        @Override
        public AppBaseResponse createFromParcel(Parcel source) {
            return new AppBaseResponse(source);
        }

        @Override
        public AppBaseResponse[] newArray(int size) {
            return new AppBaseResponse[size];
        }
    };
}
