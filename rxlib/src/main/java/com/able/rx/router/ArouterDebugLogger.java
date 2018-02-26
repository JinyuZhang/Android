/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;

import com.alibaba.android.arouter.facade.template.ILogger;
import com.orhanobut.logger.Logger;

/**
 * Created by ZhangJinyu on 2017/12/29.
 */

public class ArouterDebugLogger implements ILogger {
    private boolean isShowLog;
    private boolean isShowStackTrace;

    @Override
    public void showLog(boolean isShowLog) {
        this.isShowLog = isShowLog;
    }

    @Override
    public void showStackTrace(boolean isShowStackTrace) {
        this.isShowStackTrace = isShowStackTrace;
    }

    @Override
    public void debug(String tag, String message) {
        if (isShowLog) {
            Logger.d(message, tag);
        }
    }

    @Override
    public void info(String tag, String message) {
        if (isShowLog) {
            Logger.i(message, tag);
        }
    }

    @Override
    public void warning(String tag, String message) {
        if (isShowLog) {
            Logger.w(message, tag);
        }
    }

    @Override
    public void error(String tag, String message) {
        if (isShowLog) {
            Logger.e(message, tag);
        }
    }

    @Override
    public void monitor(String message) {
        if (isShowLog) {
            Logger.d(message);
        }
    }

    @Override
    public boolean isMonitorMode() {
        return false;
    }

    @Override
    public String getDefaultTag() {
        return "ARouterLogger";
    }
}
