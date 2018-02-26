package com.able.android.presenter;

import com.able.android.bean.response.UserLoginBean;
import com.able.android.model.LoginModel;
import com.able.android.model.base.AppNetResultCallback;
import com.able.android.presenter.base.AppCommonPresenter;
import com.able.android.presenter.view.ILoginView;
import com.able.rx.bean.LoadingType;
import com.able.rx.bean.TipType;
import com.able.rx.view.ClickCallback;
import com.able.utils.EmptyUtils;

/**
 * Created by haoyaun on 2018/1/26.
 */

public class LoginPresenter extends AppCommonPresenter<ILoginView> implements AppNetResultCallback<UserLoginBean>, ClickCallback {

    private LoginModel loginModel;
    private String username;
    private String password;

    public LoginPresenter(ILoginView contentView) {
        super(contentView);
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();
        contentView.setUsername(username);
        contentView.setPassword(password);
    }

    @Override
    public void onDestroy() {
        loginModel.onDestroy();
    }

    @Override
    public String getTag() {
        return loginModel.getTag();
    }

    @Override
    public void netPresenterCreated() {
        loginModel = new LoginModel(this);
    }

    @Override
    public void dismissLoading() {
        contentView.dismiss(getTag(), LoadingType.CONTENT);
    }

    public void login() {
        username = contentView.getUsername();
        password = contentView.getPassword();
        String deviceId = contentView.getDeviceId();
        if (EmptyUtils.isEmpty(username)) {
            contentView.showMsg(TipType.INFO, "请输入用户名", null);
            return;
        }
        if (EmptyUtils.isEmpty(password)) {
            contentView.showMsg(TipType.INFO, "请输入密码", null);
            return;
        }
        contentView.showLoading(getTag(), LoadingType.CONTENT, "正在登录中,请稍候...", null);
        loginModel.login(username, password);
    }

    @Override
    public void netResultSuccess(UserLoginBean userLoginBean, String msg) {
        contentView.dismiss(getTag(), LoadingType.CONTENT);
        loginModel.saveLoginInfo(userLoginBean, username, password);
        contentView.showMsg(TipType.SUCCESS, "登录成功", null);
        contentView.loginSuccess();
    }

    @Override
    public void netResultException(Throwable throwable, String titleMsg, String detailMsg) {
        dismissLoading();
        contentView.showMsg(titleMsg, detailMsg, "重试", this);
    }

    @Override
    public void netResultFail(int code, String msg) {
        dismissLoading();
        contentView.showMsg("登录失败", msg, "重试", this);
    }

    @Override
    public void clickCall() {
        login();
    }
}
