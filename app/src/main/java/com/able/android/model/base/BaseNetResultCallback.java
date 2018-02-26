package com.able.android.model.base;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public interface BaseNetResultCallback {
    void netResultFail(int code, String msg);

    void netResultException(Throwable throwable, String titleMsg, String detailMsg);
}
