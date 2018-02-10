package com.able.rx;

import android.content.Context;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class RxLibContext {
    public static Context context;

    public static void setContext(Context context) {
        RxLibContext.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
