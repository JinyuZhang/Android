/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.tools.event;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private final Map<String, Subject<Object>> stringSubjectMap;
    private static volatile RxBus instance;

    private RxBus() {
        stringSubjectMap = new HashMap();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public <T> void register(@NonNull Class<T> clazz, Consumer<T> next) {
        register(clazz.getName(), clazz, next, null);
    }

    public <T> void register(@NonNull String tag, @NonNull Class<T> clazz, Consumer<T> next) {
        register(tag, clazz, next, null);
    }

    public <T> void register(@NonNull Class<T> clazz, Consumer<T> next, Consumer<Throwable> error) {
        register(clazz.getName(), clazz, next, error);
    }

    public <T> void register(@NonNull String tag, @NonNull Class<T> clazz, Consumer<T> next, Consumer<Throwable> error) {
        Subject<Object> objectSubject;
        if (stringSubjectMap.containsKey(tag)) {
            objectSubject = stringSubjectMap.get(tag);
        } else {
            objectSubject = PublishSubject.create().toSerialized();
            stringSubjectMap.put(tag, objectSubject);
        }
        Flowable<T> tFollowable = objectSubject.toFlowable(BackpressureStrategy.BUFFER)
                .ofType(clazz).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Disposable disposable;
        if (error == null) {
            disposable = tFollowable.subscribe(next);
        } else {
            disposable = tFollowable.subscribe(next, error);
        }

        addSubscription(tag, disposable);
    }

    private void addSubscription(String tag, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        if (mSubscriptionMap.get(tag) != null) {
            mSubscriptionMap.get(tag).add(disposable);
        } else {
            //一次性容器,可以持有多个并提供 添加和移除。
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            mSubscriptionMap.put(tag, disposables);
        }
    }

    public void unRegister(@NonNull Class clazz) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = clazz.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }

    public void unRegister(String tag) {
        if (mSubscriptionMap == null) {
            return;
        }

        if (!mSubscriptionMap.containsKey(tag)) {
            return;
        }
        if (mSubscriptionMap.get(tag) != null) {
            mSubscriptionMap.get(tag).dispose();
        }
        mSubscriptionMap.remove(tag);
    }

    public void post(@NonNull Object o) {
        post(o.getClass().getName(), o);
    }

    public void post(@NonNull String tag, @NonNull Object o) {
        for (String key : stringSubjectMap.keySet()) {
            if (tag.equals(key)) {
                stringSubjectMap.get(key).onNext(o);
            }
        }
    }

}
