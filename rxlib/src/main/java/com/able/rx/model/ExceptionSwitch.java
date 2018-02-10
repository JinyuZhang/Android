package com.able.rx.model;

import com.able.rx.bean.ExceptionWrapper;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public abstract class ExceptionSwitch {

    public abstract ExceptionWrapper getExceptionMsg(Exception e);

    public abstract ExceptionWrapper getExceptionMsg(Throwable e);
}
