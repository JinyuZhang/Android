package com.able.rx.presenter;

import com.able.rx.view.ILoadingView;

/**
 * Created by guanlijie on 2018/2/10.
 */

public abstract class BaseNetPresenter<V extends ILoadingView> extends BasePresenter<V> {
    protected V contentView;

    public BaseNetPresenter(V view) {
        super(view);
        this.contentView = view;
        netPresenterCreated();
    }

    public abstract void netPresenterCreated();
}
