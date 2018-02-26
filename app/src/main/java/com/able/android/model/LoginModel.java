package com.able.android.model;

import com.able.android.api.ApiService;
import com.able.android.bean.base.CommonRsp;
import com.able.android.bean.response.UserLoginBean;
import com.able.android.model.base.AppCommonModel;
import com.able.android.model.base.AppNetResultCallback;
import com.able.rx.utils.JsonUtils;
import com.able.utils.EmptyUtils;
import com.able.utils.SPUtils;

import io.reactivex.Observable;

import static com.able.android.api.ApiService.LOGIN;


/**
 * Created by haoyaun on 2018/1/26.
 */

public class LoginModel extends AppCommonModel<UserLoginBean> {

    public LoginModel(AppNetResultCallback<UserLoginBean> appNetResultCallback) {
        super(appNetResultCallback);
    }

    @Override
    public Observable<CommonRsp<UserLoginBean>> createObservableCall(ApiService apiService) {
        return apiService.login(username, password);
    }

    @Override
    public String getTag() {
        return LOGIN;
    }

    private String username, password;

    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        execute();
    }

    public void saveLoginInfo(UserLoginBean userLoginBean, String username, String password) {
        SPUtils.putString("Login", JsonUtils.getInstance().toJson(userLoginBean));
        SPUtils.putString("username", username);
        SPUtils.putString("password", password);
        SPUtils.putString("userId", userLoginBean.getId());
    }

    public String getUsername() {
        return SPUtils.getString("username");
    }

    public String getPassword() {
        return SPUtils.getString("password");
    }

    public UserLoginBean getLoginInfo() {
        String login = SPUtils.getString("Login");
        if (EmptyUtils.isNotEmpty(login)) {
            try {
                return JsonUtils.getInstance().format(login, UserLoginBean.class);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
