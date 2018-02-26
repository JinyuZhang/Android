/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.android.bean.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ZhangJinyu on 2018/1/5.
 */

public class PageWrapper<T extends NoneRspBean> extends NoneRspBean implements Parcelable {
    private int totalPage;
    private int totalSize;
    private int currentPage;
    private int currentPageSize;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List<T> results;

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCurrentPageSize() {
        return currentPageSize;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public List<T> getResults() {
        return results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalPage);
        dest.writeInt(this.totalSize);
        dest.writeInt(this.currentPage);
        dest.writeInt(this.currentPageSize);
        dest.writeByte(this.hasNextPage ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasPreviousPage ? (byte) 1 : (byte) 0);
        dest.writeString(this.results.getClass().getName());
        dest.writeList(this.results);
    }

    public PageWrapper() {
    }

    protected PageWrapper(Parcel in) {
        this.totalPage = in.readInt();
        this.totalSize = in.readInt();
        this.currentPage = in.readInt();
        this.currentPageSize = in.readInt();
        this.hasNextPage = in.readByte() != 0;
        this.hasPreviousPage = in.readByte() != 0;
        String dataName = in.readString();
        try {
            this.results = in.readParcelable(Class.forName(dataName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<PageWrapper> CREATOR = new Creator<PageWrapper>() {
        @Override
        public PageWrapper createFromParcel(Parcel source) {
            return new PageWrapper(source);
        }

        @Override
        public PageWrapper[] newArray(int size) {
            return new PageWrapper[size];
        }
    };
}
