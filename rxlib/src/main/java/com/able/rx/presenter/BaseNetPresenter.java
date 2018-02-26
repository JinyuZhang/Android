package com.able.rx.presenter;

import com.able.rx.bean.TipType;
import com.able.rx.view.ILoadingView;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public abstract class BaseNetPresenter<V extends ILoadingView> extends BasePresenter<V> {
    public BaseNetPresenter(V view) {
        super(view);
        netPresenterCreated();
    }

    public abstract void netPresenterCreated();

    public abstract void dismissLoading();

    public void netResultFail(int code, String msg) {
        dismissLoading();
        contentView.showMsg(TipType.FAIL, msg, null);
    }

    public void netResultException(Throwable throwable, String titleMsg, String detailMsg) {
        dismissLoading();
        contentView.showMsg(TipType.FAIL, titleMsg, detailMsg);
    }
}
