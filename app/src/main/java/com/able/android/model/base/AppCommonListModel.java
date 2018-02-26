package com.able.android.model.base;


import com.able.android.api.ApiService;
import com.able.android.bean.base.CommonRsp;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public abstract class AppCommonListModel<T> extends AppNetModel<ApiService, CommonRsp<T>> {
    private AppNetListResultCallback<T> appNetListResultCallback;

    public AppCommonListModel(AppNetListResultCallback<T> appNetListResultCallback) {
        super(appNetListResultCallback);
        this.appNetListResultCallback = appNetListResultCallback;
    }

    @Override
    public void netResult(CommonRsp<T> tCommonRsp) {
        if (appNetListResultCallback != null) {
            if (tCommonRsp.isSuccessul()) {
                appNetListResultCallback.netResultSuccess(tCommonRsp.getData(), tCommonRsp.getTotal(), tCommonRsp.getErrorMessage());
            } else {
                appNetListResultCallback.netResultFail(tCommonRsp.getErrorCode(), tCommonRsp.getErrorMessage());
            }
        }
    }
}
