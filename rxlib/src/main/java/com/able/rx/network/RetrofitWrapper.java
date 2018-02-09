/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.network;

import com.able.rx.RxLibContext;
import com.able.rx.utils.JsonUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jinyu Zhang on 2017/8/3.
 */

public class RetrofitWrapper {
    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private final NovateCookieManger novateCookieManger;
    private String hostUrl;

    public RetrofitWrapper(boolean isDebug, String hostUrl) {
        this.hostUrl = hostUrl;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (isDebug) {
            httpClient.addInterceptor(new LoggingInterceptor());
        }
        novateCookieManger = new NovateCookieManger(RxLibContext.getContext());
        httpClient.cookieJar(novateCookieManger);
        httpClient.connectTimeout(12, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();
        builder = new Retrofit.Builder();
        builder.client(client)
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.getInstance().getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(hostUrl);
        retrofit = builder.build();
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public NovateCookieManger getNovateCookieManger() {
        return novateCookieManger;
    }


    public <T> T createService(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
}
