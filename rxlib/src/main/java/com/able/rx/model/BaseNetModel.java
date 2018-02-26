package com.able.rx.model;

import com.able.rx.network.RetrofitWrapper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class BaseNetModel<S, T> extends BaseModel implements NetResultCallback<T> {
    private RetrofitWrapper retrofitWrapper;
    private final S apiService;
    private NetResultCallback<T> netResultCallback;
    private Disposable subscribe;

    public BaseNetModel() {
        retrofitWrapper = createRetrofitWrapper(retrofitWrapper);
        apiService = retrofitWrapper.createService(getApiService());
    }

    public void setNetResultCallback(NetResultCallback<T> netResultCallback) {
        this.netResultCallback = netResultCallback;
    }

    public abstract Class<S> getApiService();

    public abstract RetrofitWrapper createRetrofitWrapper(RetrofitWrapper retrofitWrapper);

    public abstract Observable<T> createObservableCall(S apiService);

    public void execute() {
        subscribe = createObservableCall(apiService).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                if (BaseNetModel.this.netResultCallback != null) {
                    BaseNetModel.this.netResultCallback.netResult(t);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (BaseNetModel.this.netResultCallback != null) {
                    BaseNetModel.this.netResultCallback.netResultException(throwable);
                }
            }
        });
    }

    public void onDestroy() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
