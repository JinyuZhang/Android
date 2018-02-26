package com.able.android.presenter.view;


import com.able.rx.view.ILoadingView;

/**
 * Created by haoyaun on 2018/1/26.
 */

public interface ILoginView extends ILoadingView {
    String getUsername();

    String getPassword();

    String getDeviceId();

    void setUsername(String username);

    void setPassword(String password);

    void loginSuccess();
}
