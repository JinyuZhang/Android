package com.able.android;

import android.app.Application;
import android.content.Context;

import com.able.rx.router.RxLibContext;
import com.able.utils.UtilsApplication;

import able.com.debug.DebugApplication;
import able.com.debug.logger.AndroidLogAdapter;
import able.com.debug.logger.Logger;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DebugApplication.init(this);
        UtilsApplication.init(this);
        RxLibContext.setContext(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getContext() {
        return context;
    }
}
