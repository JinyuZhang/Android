package com.able.rx.model;

/**
 * Created by guanlijie on 2018/2/9.
 */

public interface NetResultCallback<T> {

    void netResult(T t);

    void netResultException(Throwable throwable);
}
