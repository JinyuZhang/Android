package com.able.android.model.base;

import com.able.rx.bean.ExceptionWrapper;
import com.able.rx.model.ExceptionSwitch;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public class AppExceptionSwitch extends ExceptionSwitch {
    @Override
    public ExceptionWrapper getExceptionMsg(Exception e) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper();
        return exceptionWrapper;
    }

    @Override
    public ExceptionWrapper getExceptionMsg(Throwable e) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper();
        if (e instanceof UnknownHostException) {
            exceptionWrapper.setWrapperData("网络连接错误", "请检查网络连接");
        } else if (e instanceof HttpException) {
            exceptionWrapper.setWrapperData("错误", "服务器内部错误");
        } else if (e instanceof ConnectException) {
            exceptionWrapper.setWrapperData("网络连接错误", "请检查网络连接");
        } else if (e instanceof SocketTimeoutException) {
            exceptionWrapper.setWrapperData("网络连接超时", "请检查网络连接");
        }
        return exceptionWrapper;
    }
}
