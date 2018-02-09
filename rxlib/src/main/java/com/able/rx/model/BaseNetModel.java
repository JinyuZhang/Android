package com.able.rx.model;

import com.able.rx.network.RetrofitWrapper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guanlijie on 2018/2/9.
 */

public abstract class BaseNetModel<A, T> extends BaseModel {
    private RetrofitWrapper retrofitWrapper;
    private final A apiService;
    private Observable<T> observableCall;
    private NetResultCallback<T> netResultCallback;

    public BaseNetModel(NetResultCallback<T> netResultCallback) {
        this.netResultCallback = netResultCallback;
        retrofitWrapper = createRetrofitWrapper(retrofitWrapper);
        apiService = retrofitWrapper.createService(getApiService());
    }

    public abstract RetrofitWrapper createRetrofitWrapper(RetrofitWrapper retrofitWrapper);

    public abstract Class<A> getApiService();

    public abstract Observable<T> createObservableCall(A apiService);

    public void excute() {
        observableCall = createObservableCall(apiService);
        observableCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
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
}
