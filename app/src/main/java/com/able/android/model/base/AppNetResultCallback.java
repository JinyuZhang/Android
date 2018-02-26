package com.able.android.model.base;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public interface AppNetResultCallback<T> extends BaseNetResultCallback {
    void netResultSuccess(T t, String msg);
}
