package com.able.rx.presenter;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class BasePresenter<V> {
    private V contentView;

    public BasePresenter(V view) {
        this.contentView = view;
    }
}
