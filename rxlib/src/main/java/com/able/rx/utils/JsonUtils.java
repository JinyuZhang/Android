/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.utils;

import com.able.rx.utils.json.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Jinyu Zhang on 2017/8/12.
 */

public class JsonUtils {
    private static final JsonUtils ourInstance = new JsonUtils();
    private final Gson gson;

    public static JsonUtils getInstance() {
        return ourInstance;
    }

    private JsonUtils() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    public Gson getGson() {
        return gson;
    }

    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public <T> T format(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
