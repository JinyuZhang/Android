package com.able.android.api;

/**
 * Created by guanlijie on 2018/2/9.
 */

public interface Api {
    String HOST = "http://api.ngrok.zhangjinyu.ren";
    String PORT = "8080";
    String VERSION = "api/v1";
    String HOST_URL = HOST + ":" + PORT + "/" + VERSION + "/";


    String LOGIN = "login";
}
