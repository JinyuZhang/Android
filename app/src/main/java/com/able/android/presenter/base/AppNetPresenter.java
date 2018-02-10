package com.able.android.presenter.base;

import com.able.rx.presenter.BaseNetPresenter;
import com.able.rx.view.ILoadingView;

/**
 * Created by ZhangJinyu on 2018/2/10.
 */

public abstract class AppNetPresenter<V extends ILoadingView> extends BaseNetPresenter<V> {
    public AppNetPresenter(V view) {
        super(view);

    }


}
