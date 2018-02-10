package com.able.android.presenter.view;

import com.able.rx.view.ILoadingView;

/**
 * Created by guanlijie on 2018/2/10.
 */

public interface ILoginView extends ILoadingView {
    String getUsername();

    String getPassword();

    String setUsername();

    String setPassword();

    void loginSuccess();
}
