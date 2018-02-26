package com.able.android.api;


import com.able.android.bean.base.CommonRsp;
import com.able.android.bean.response.UserLoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public interface ApiService {
    String HOST = "http://121.40.195.149";
    String PORT = "8080";
    String HOST_URL = HOST + ":" + PORT + "/";

    String SYS_USER = "/xypjs/sysUser/app/sysUser.json";
    String LOGIN = SYS_USER + "?act=login";

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN)
    Observable<CommonRsp<UserLoginBean>> login(@Field("username") String username, @Field("password") String password);

}
