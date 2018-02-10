package com.able.android.model.base;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public interface AppNetResultCallback<T> {
    void netResultSuccess(T t, String msg);

    void netResultFail(int code, String msg);

    void netResultException(Throwable throwable, String titleMsg, String detailMsg);
}
