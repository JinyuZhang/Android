package com.able.android.model.base;

import com.able.android.api.ApiService;
import com.able.android.bean.base.CommonRsp;
import com.able.utils.EmptyUtils;
import com.able.utils.SPUtils;


/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public abstract class AppCommonModel<T> extends AppNetModel<ApiService, CommonRsp<T>> {
    private AppNetResultCallback<T> appNetResultCallback;

    public AppCommonModel(AppNetResultCallback<T> appNetResultCallback) {
        super(appNetResultCallback);
        this.appNetResultCallback = appNetResultCallback;
    }

    @Override
    public void netResult(CommonRsp<T> tCommonRsp) {
        if (appNetResultCallback != null) {
            if (tCommonRsp.isSuccessul()) {
                String orgName = tCommonRsp.getOrgname();
                if (EmptyUtils.isNotEmpty(orgName)) {
                    SPUtils.putString("org_name", orgName);
                }
                appNetResultCallback.netResultSuccess(tCommonRsp.getData(), tCommonRsp.getErrorMessage());
            } else {
                appNetResultCallback.netResultFail(tCommonRsp.getErrorCode(), tCommonRsp.getErrorMessage());
            }
        }
    }
}
