package com.able.rx.model;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class BaseModel {
    public BaseModel() {

    }

    private ExceptionSwitch exceptionSwitch;

    public void setExceptionSwitch(ExceptionSwitch exceptionSwitch) {
        this.exceptionSwitch = exceptionSwitch;
    }

    public ExceptionSwitch getExceptionSwitch() {
        return exceptionSwitch;
    }
}
