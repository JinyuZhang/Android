package com.able.android.ui;

import android.os.Bundle;

import com.able.android.R;
import com.able.android.presenter.LoginPresenter;
import com.able.android.presenter.view.ILoginView;
import com.able.rx.activity.BaseLoadingActivity;

public class MainActivity extends BaseLoadingActivity implements ILoginView {


    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
        setTopbarTitle("登录");
    }

    private void login() {
        if (loginPresenter == null) {
            loginPresenter = new LoginPresenter(this);
        }
        loginPresenter.login();
    }

    @Override
    public String getUsername() {
        return "sdflkj";
    }

    @Override
    public String getPassword() {
        return "dsfjlasjlkd";
    }

    @Override
    public String getDeviceId() {
        return null;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void loginSuccess() {

    }
}
