package com.able.android;

import android.content.Context;

import com.able.rx.RxLibContext;

import able.com.debug.DebugApplication;
import able.com.debug.logger.AndroidLogAdapter;
import able.com.debug.logger.Logger;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class App extends DebugApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RxLibContext.setContext(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getContext() {
        return context;
    }
}
