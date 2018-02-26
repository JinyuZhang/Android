/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.android.bean.base;

import android.os.Parcel;

/**
 * Created by ZhangJinyu on 2017/12/30.
 */

public class NoneRspBean extends AppBean {

    public NoneRspBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected NoneRspBean(Parcel in) {
        super(in);
    }

}
