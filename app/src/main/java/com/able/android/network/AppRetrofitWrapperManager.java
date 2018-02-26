package com.able.android.network;

import com.able.android.api.ApiService;
import com.able.rx.network.RetrofitWrapper;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class AppRetrofitWrapperManager {
    private static AppRetrofitWrapperManager instance;

    private AppRetrofitWrapperManager() {

    }

    public static AppRetrofitWrapperManager getInstance() {
        if (instance == null) {
            synchronized (AppRetrofitWrapperManager.class) {
                if (instance == null) {
                    instance = new AppRetrofitWrapperManager();
                }
            }
        }
        return instance;
    }

    private RetrofitWrapper appRetrofitWrapper;

    public RetrofitWrapper getAppRetrofitWrapper() {
        if (appRetrofitWrapper == null) {
            appRetrofitWrapper = new RetrofitWrapper(true, ApiService.HOST_URL);
        }
        return appRetrofitWrapper;
    }

}
