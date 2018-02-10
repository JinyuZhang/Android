package com.able.android.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.able.android.R;
import com.able.android.presenter.LoginPresenter;
import com.able.android.presenter.view.ILoginView;
import com.able.rx.activity.BaseLoadingActivity;
import com.able.rx.convert.ConvertCallback;
import com.able.rx.convert.RxConvert;

import able.com.debug.logger.Logger;

public class MainActivity extends BaseLoadingActivity implements ILoginView {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        ddd();
    }

    private void ddd() {
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.login();
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
    public String getUsername() {
        return "zhangjwinyu";
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public String setUsername() {
        return null;
    }

    @Override
    public String setPassword() {
        return null;
    }

    @Override
    public void loginSuccess() {

    }
}
