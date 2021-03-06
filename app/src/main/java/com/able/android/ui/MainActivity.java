package com.able.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.able.android.R;
import com.able.android.presenter.LoginPresenter;
import com.able.android.presenter.view.ILoginView;
import com.able.rx.activity.BaseLoadingActivity;
import com.able.rx.tools.event.RxBus;
import com.able.rx.view.ContentView;

import butterknife.BindView;

public class MainActivity extends BaseLoadingActivity implements ILoginView {

    @BindView(R.id.btn_login)
    Button button;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
        setTopbarTitle("登录");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(RxBusActivity.class);
    }

    @Override
    public void contentViewInit(ContentView contentView) {
        super.contentViewInit(contentView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        if (loginPresenter == null) {
            loginPresenter = new LoginPresenter(this);
        }
        loginPresenter.login();
        startActivity(new Intent(this, RxBusActivity.class));
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
