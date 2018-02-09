/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.network;

import com.able.rx.utils.EncodeUtils;
import com.able.rx.utils.JsonUtils;

import java.io.IOException;

import able.com.debug.logger.Logger;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Jinyu Zhang on 2017/8/5.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间

        String method = request.method();
        if ("POST".equals(method) || "PATCH".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                Logger.d(JsonUtils.getInstance().toJson(body));
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Logger.d(String.format("发送请求 %s \n请求方式:%s\nRequestParams:{%s}",
                        request.url(), method, EncodeUtils.urlDecode(sb.toString())));
            }
        } else {
            Logger.d(String.format("发送请求 %s on %s%n%s \n请求方式:%s",
                    EncodeUtils.urlDecode(request.url().toString()), chain.connection(), request.headers(), method));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        Logger.d(String.format("接收响应:[%s]%n用时:%.1fms%n%s%n返回json:%n%s", EncodeUtils.urlDecode(response.request().url().toString()), (t2 - t1) / 1e6d, response.headers(), responseBody.string()));
        return response;
    }
}
