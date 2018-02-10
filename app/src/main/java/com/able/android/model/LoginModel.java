package com.able.android.model;

import com.able.android.api.Api;
import com.able.android.api.ApiService;
import com.able.android.bean.base.AppBaseResponse;
import com.able.android.bean.response.LoginRsp;
import com.able.android.model.base.AppNetModel;
import com.able.android.model.base.AppNetResultCallback;

import io.reactivex.Observable;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class LoginModel extends AppNetModel<AppBaseResponse<LoginRsp>, LoginRsp> {


    public LoginModel(AppNetResultCallback<LoginRsp> appNetResultCallback) {
        super(appNetResultCallback);
    }

    @Override
    public String getTag() {
        return Api.LOGIN;
    }

    @Override
    public Observable<AppBaseResponse<LoginRsp>> createObservableCall(ApiService apiService) {
        return apiService.login(username, password);
    }

    private String username, password;

    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        excute();
    }

}
