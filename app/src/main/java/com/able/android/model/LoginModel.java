package com.able.android.model;

import com.able.android.api.Api;
import com.able.android.bean.base.AppBaseResponse;
import com.able.android.bean.response.LoginRsp;
import com.able.rx.model.NetResultCallback;

import io.reactivex.Observable;

/**
 * Created by guanlijie on 2018/2/9.
 */

public class LoginModel extends AppNetModel<AppBaseResponse<LoginRsp>> {
    public LoginModel(NetResultCallback<AppBaseResponse<LoginRsp>> netResultCallback) {
        super(netResultCallback);
    }

    @Override
    public Observable<AppBaseResponse<LoginRsp>> createObservableCall(Api apiService) {
        return apiService.login(username, password);
    }

    private String username, password;

    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        excute();
    }

}
