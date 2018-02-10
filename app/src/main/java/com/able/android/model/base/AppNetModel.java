package com.able.android.model.base;

import com.able.android.api.ApiService;
import com.able.android.bean.base.AppBaseResponse;
import com.able.android.network.AppRetrofitWrapperManager;
import com.able.rx.bean.ExceptionWrapper;
import com.able.rx.model.BaseNetModel;
import com.able.rx.model.NetResultCallback;
import com.able.rx.network.RetrofitWrapper;

import able.com.debug.logger.Logger;


/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class AppNetModel<T extends AppBaseResponse<D>, D> extends BaseNetModel<ApiService, T> implements NetResultCallback<T> {
    private AppNetResultCallback<D> appNetResultCallback;

    public AppNetModel(final AppNetResultCallback<D> appNetResultCallback) {
        this.appNetResultCallback = appNetResultCallback;
        setNetResultCallback(this);
        setExceptionSwitch(new AppExceptionSwitch());
    }

    @Override
    public void netResult(T t) {
        if (appNetResultCallback != null) {
            if (t.isSuccess()) {
                appNetResultCallback.netResultSuccess(t.getData(), t.getMsg());
            } else {
                appNetResultCallback.netResultFail(t.getStatus(), t.getMsg());
            }
        }
    }

    @Override
    public void netResultException(Throwable throwable) {
        Logger.d(throwable.toString());
        if (appNetResultCallback != null) {
            if (getExceptionSwitch() == null) {
                appNetResultCallback.netResultException(throwable, null, null);
            } else {
                ExceptionWrapper exceptionMsg = getExceptionSwitch().getExceptionMsg(throwable);
                appNetResultCallback.netResultException(throwable, exceptionMsg.getTitleMsg(), exceptionMsg.getDetailMsg());
            }
        }
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


}
