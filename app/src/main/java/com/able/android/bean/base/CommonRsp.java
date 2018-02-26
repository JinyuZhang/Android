/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.android.bean.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.able.rx.utils.JsonUtils;

/**
 * Created by ZhangJinyu on 2017/12/29.
 */

public class CommonRsp<T> extends NoneRspBean implements Parcelable {
    private int errorCode;
    private String errorMessage;
    private int total;
    private T data;
    private String org_name;

    public boolean isSuccessul() {
        return errorCode == 200;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getTotal() {
        return total;
    }

    public T getData() {
        return data;
    }

    public String getOrgname() {
        return org_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorMessage);
        dest.writeInt(this.total);
        dest.writeString(data.getClass().getName());
        dest.writeString(JsonUtils.getInstance().toJson(data));
    }

    public CommonRsp() {
    }

    protected CommonRsp(Parcel in) {
        super(in);
        this.errorCode = in.readInt();
        this.errorMessage = in.readString();
        this.total = in.readInt();
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

    public static final Creator<CommonRsp> CREATOR = new Creator<CommonRsp>() {
        @Override
        public CommonRsp createFromParcel(Parcel source) {
            return new CommonRsp(source);
        }

        @Override
        public CommonRsp[] newArray(int size) {
            return new CommonRsp[size];
        }
    };
}
