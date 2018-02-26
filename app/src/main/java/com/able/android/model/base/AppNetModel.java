package com.able.android.model.base;

import com.able.android.api.ApiService;
import com.able.android.network.AppRetrofitWrapperManager;
import com.able.rx.bean.ExceptionWrapper;
import com.able.rx.model.BaseNetModel;
import com.able.rx.network.RetrofitWrapper;


/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class AppNetModel<S, T> extends BaseNetModel<ApiService, T> {
    private BaseNetResultCallback baseNetResultCallback;

    public AppNetModel(final BaseNetResultCallback baseNetResultCallback) {
        this.baseNetResultCallback = baseNetResultCallback;
        setNetResultCallback(this);
        setExceptionSwitch(new AppExceptionSwitch());
    }

    @Override
    public Class<ApiService> getApiService() {
        return ApiService.class;
    }

    @Override
    public RetrofitWrapper createRetrofitWrapper(RetrofitWrapper retrofitWrapper) {
        if (retrofitWrapper == null) {
            retrofitWrapper = AppRetrofitWrapperManager.getInstance().getAppRetrofitWrapper();
        }
        return retrofitWrapper;
    }

    public abstract String getTag();

    @Override
    public void netResultException(Throwable throwable) {
        if (baseNetResultCallback != null) {
            if (getExceptionSwitch() == null) {
                baseNetResultCallback.netResultException(throwable, null, null);
            } else {
                ExceptionWrapper exceptionMsg = getExceptionSwitch().getExceptionMsg(throwable);
                baseNetResultCallback.netResultException(throwable, exceptionMsg.getTitleMsg(), exceptionMsg.getDetailMsg());
            }
        }
    }
}
