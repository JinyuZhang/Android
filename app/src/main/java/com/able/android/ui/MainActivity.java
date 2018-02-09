package com.able.android.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.able.android.R;
import com.able.android.bean.base.AppBaseResponse;
import com.able.android.bean.response.LoginRsp;
import com.able.android.model.LoginModel;
import com.able.rx.activity.BaseLoadingActivity;
import com.able.rx.bean.LoadingType;
import com.able.rx.convert.ConvertCallback;
import com.able.rx.convert.RxConvert;
import com.able.rx.model.NetResultCallback;
import com.able.rx.utils.JsonUtils;
import com.able.rx.view.ClickCallback;

import able.com.debug.logger.Logger;

public class MainActivity extends BaseLoadingActivity implements NetResultCallback<AppBaseResponse<LoginRsp>>, ClickCallback {

    private TextView textView;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        ddd();
    }

    private void ddd() {
        loginModel = new LoginModel(this);
        loginModel.login("zhangjinyu", "123456");
        showLoading(getClass().getName(), LoadingType.DIALOG, "正在登录,请稍候", "");
    }

    private void convert() {
        RxConvert.getInstance().convert(new ConvertCallback<String, Integer>() {
            @Override
            public String getSource() {
                return "1,2,3,4";
            }

            @Override
            public Integer convertAction(String source) {
                return Integer.parseInt(source);
            }

            @Override
            public void convertComplete(boolean success, Integer s, Throwable throwable) {
                String result = "success:" + success + "\ns:" + s;
                textView.setText(result);
                if (success) {
                    Logger.d(result);
                } else {
                    Logger.d(throwable.toString());
                }
            }
        });
    }

    @Override
    public void netResult(AppBaseResponse<LoginRsp> loginRspAppBaseResponse) {
        Logger.d(JsonUtils.getInstance().toJson(loginRspAppBaseResponse));
        textView.setText(JsonUtils.getInstance().toJson(loginRspAppBaseResponse));
        dismiss(getClass().getName(), LoadingType.DIALOG);
    }

    @Override
    public void netResultException(Throwable throwable) {
        Logger.e(throwable.toString());
    }

    @Override
    public void clickCall() {
        Logger.d("重试");
    }
}
