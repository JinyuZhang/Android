/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.android.bean.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZhangJinyu on 2017/12/29.
 */

public class AppBean implements Parcelable {

    public AppBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected AppBean(Parcel in) {
    }

    public static final Creator<AppBean> CREATOR = new Creator<AppBean>() {
        @Override
        public AppBean createFromParcel(Parcel source) {
            return new AppBean(source);
        }

        @Override
        public AppBean[] newArray(int size) {
            return new AppBean[size];
        }
    };
}
