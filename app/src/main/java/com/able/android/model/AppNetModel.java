package com.able.android.model;

import com.able.android.api.Api;
import com.able.android.network.AppRetrofitWrapperManager;
import com.able.rx.model.BaseNetModel;
import com.able.rx.model.NetResultCallback;
import com.able.rx.network.RetrofitWrapper;


/**
 * Created by guanlijie on 2018/2/9.
 */

public abstract class AppNetModel<T> extends BaseNetModel<Api, T> {

    public AppNetModel(NetResultCallback<T> netResultCallback) {
        super(netResultCallback);
    }

    @Override
    public Class<Api> getApiService() {
        return Api.class;
    }

    @Override
    public RetrofitWrapper createRetrofitWrapper(RetrofitWrapper retrofitWrapper) {
        if (retrofitWrapper == null) {
            retrofitWrapper = AppRetrofitWrapperManager.getInstance().getAppRetrofitWrapper();
        }
        return retrofitWrapper;
    }


}
