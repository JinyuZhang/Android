package com.able.android.model.base;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public interface AppNetListResultCallback<T> extends BaseNetResultCallback {
    void netResultSuccess(T t, int total, String msg);
}
