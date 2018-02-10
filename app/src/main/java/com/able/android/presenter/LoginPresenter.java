package com.able.android.presenter;

import com.able.android.bean.base.AppBaseResponse;
import com.able.android.bean.response.LoginRsp;
import com.able.android.model.LoginModel;
import com.able.android.model.base.AppNetResultCallback;
import com.able.android.presenter.base.AppNetPresenter;
import com.able.android.presenter.view.ILoginView;
import com.able.rx.bean.LoadingType;
import com.able.rx.bean.TipType;
import com.able.rx.view.ClickCallback;

/**
 * Created by guanlijie on 2018/2/10.
 */

public class LoginPresenter extends AppNetPresenter<ILoginView> implements AppNetResultCallback<LoginRsp>, ClickCallback {

    private LoginModel loginModel;

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    public void login() {
        String username = contentView.getUsername();
        String password = contentView.getPassword();
        contentView.showLoading(loginModel.getTag(), LoadingType.CONTENT, "正在登陆,请稍后...", null);
        loginModel.login(username, password);
    }


    @Override
    public void netPresenterCreated() {
        loginModel = new LoginModel(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginModel.onDestroy();
    }


    public void netResult(AppBaseResponse<LoginRsp> loginRspAppBaseResponse) {

    }

    @Override
    public void netResultSuccess(LoginRsp loginRsp, String msg) {
        contentView.dismiss(loginModel.getTag(), LoadingType.CONTENT);
        contentView.showMsg(TipType.CONTENT, "登陆成功", msg);
    }

    @Override
    public void netResultFail(int code, String msg) {
        contentView.dismiss(loginModel.getTag(), LoadingType.CONTENT);
        contentView.showMsg("登陆失败", msg, "重试", this);
    }

    @Override
    public void netResultException(Throwable throwable, String titleMsg, String detailMsg) {
        contentView.dismiss(loginModel.getTag(), LoadingType.CONTENT);
        contentView.showMsg(titleMsg, detailMsg, "重试", this);
    }


    @Override
    public void clickCall() {
        login();
    }
}
