package com.able.rx.tools.timer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guanlijie on 2018/2/10.
 */

public class RxTimer {

    private Disposable delaySubscribe;
    private Disposable intervalDisposable;

    public RxTimer() {

    }

    public void postDelayed(long delay, TimeUnit timeUnit, final Runnable runnable) {
        delaySubscribe = Observable.timer(delay, timeUnit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void postInterval(long initialDelay, long period, TimeUnit timeUnit, final Runnable runnable) {
        intervalDisposable = Observable.interval(initialDelay, period, timeUnit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void onDestroy() {
        if (delaySubscribe != null && !delaySubscribe.isDisposed()) {
            delaySubscribe.dispose();
        }
        if (intervalDisposable != null && !intervalDisposable.isDisposed()) {
            intervalDisposable.dispose();
        }
    }


}
