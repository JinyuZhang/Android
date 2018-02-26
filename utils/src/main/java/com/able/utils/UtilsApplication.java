package com.able.utils;

import android.app.Application;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public class UtilsApplication {
    public static void init(Application application) {
        Utils.init(application.getApplicationContext());
    }
}
