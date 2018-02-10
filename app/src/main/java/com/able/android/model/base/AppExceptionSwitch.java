package com.able.android.model.base;

import com.able.rx.bean.ExceptionWrapper;
import com.able.rx.model.ExceptionSwitch;

import java.net.UnknownHostException;

/**
 * Created by guanlijie on 2018/2/10.
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
            exceptionWrapper.setWrapperData("网络连接错误", "请检查设备网络设置");
        }
        return exceptionWrapper;
    }
}
