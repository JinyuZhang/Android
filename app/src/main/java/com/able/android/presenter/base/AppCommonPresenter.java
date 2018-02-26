package com.able.android.presenter.base;

import com.able.android.presenter.page.PageManage;
import com.able.rx.bean.LoadingType;
import com.able.rx.presenter.BaseNetPresenter;
import com.able.rx.view.ILoadingView;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public abstract class AppCommonPresenter<V extends ILoadingView> extends BaseNetPresenter<V> {

    private PageManage pageManage;

    public AppCommonPresenter(V view) {
        super(view);
    }


    public void initPageManage() {
        if (pageManage == null) {
            pageManage = new PageManage();
        }
    }

    public PageManage getPageManage() {
        initPageManage();
        return pageManage;
    }

    public void changLoadingType(boolean isRefresh, LoadingType loadingType, String msg) {
        pageManage.setRefresh(isRefresh);
        if (pageManage.isFrist()) {
            contentView.showLoading(getTag(), loadingType, msg, null);
        } else {
            contentView.showLoading(getTag(), LoadingType.NONE, msg, null);
        }
    }

}
