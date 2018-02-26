package com.able.rx.tools.convert;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangJinyu on 2018/2/9.
 * 异步转换
 */

public class RxConvert {
    private static RxConvert instance;

    private RxConvert() {

    }

    public static RxConvert getInstance() {
        if (instance == null) {
            synchronized (RxConvert.class) {
                if (instance == null) {
                    instance = new RxConvert();
                }
            }
        }
        return instance;
    }

    /**
     * 开始转换
     *
     * @param <S> 源数据
     * @param <R> 处理后的数据
     */
    public <S, R> void convert(final ConvertCallback<S, R> convertCallback) {
        Observable.create(new ObservableOnSubscribe<S>() {
            @Override
            public void subscribe(ObservableEmitter<S> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(convertCallback.getSource());
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<S, R>() {
            @Override
            public R apply(S s) throws Exception {
                return convertCallback.convertAction(s);
            }
        }).subscribe(new Observer<R>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(R r) {
                convertCallback.convertComplete(true, r, null);
            }

            @Override
            public void onError(Throwable e) {
                convertCallback.convertComplete(false, null, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
