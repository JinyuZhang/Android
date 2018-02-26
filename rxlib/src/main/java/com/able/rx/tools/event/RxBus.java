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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus<T> {
    private HashMap<String, Disposable> mSubscriptionMap;
    private Map<String, Subject<BusEvent<T>>> stringSubjectMap;
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

    public void register(@NonNull Class clazz, Consumer<BusEvent<T>> next) {
        register(clazz, next, null);
    }

    public void register(@NonNull String tag, Consumer<BusEvent<T>> next) {
        register(tag, next, null);
    }

    public void register(@NonNull Class clazz, Consumer<BusEvent<T>> next, Consumer<Throwable> error) {
        register(clazz.getName(), next, error);
    }

    public void register(@NonNull final String tag, Consumer<BusEvent<T>> next, Consumer<Throwable> error) {
        Subject<BusEvent<T>> objectSubject;
        if (stringSubjectMap.containsKey(tag)) {
            objectSubject = stringSubjectMap.get(tag);
        } else {
            PublishSubject<BusEvent<T>> objectPublishSubject = PublishSubject.create();
            objectSubject = objectPublishSubject.toSerialized();
            stringSubjectMap.put(tag, objectSubject);
        }
        Flowable<BusEvent<T>> tFollowable = objectSubject.toFlowable(BackpressureStrategy.LATEST)
                .filter(new Predicate<BusEvent>() {
                    @Override
                    public boolean test(BusEvent busEvent) throws Exception {
                        return busEvent.getTag().equals(tag);
                    }
                }).subscribeOn(Schedulers.io())
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
            mSubscriptionMap.put(tag, disposable);
        } else {
            mSubscriptionMap.put(tag, disposable);
        }
    }

    public void unRegister(@NonNull Class clazz) {
        unRegister(clazz.getName());
    }

    public void unRegister(String tag) {
        if (mSubscriptionMap != null) {
            if (!mSubscriptionMap.containsKey(tag)) {
                return;
            }
            if (mSubscriptionMap.get(tag) != null) {
                mSubscriptionMap.get(tag).dispose();
            }
            mSubscriptionMap.remove(tag);
        }
        if (stringSubjectMap != null) {
            if (!stringSubjectMap.containsKey(tag)) {
                return;
            }
            if (stringSubjectMap.get(tag) != null) {
                stringSubjectMap.get(tag).unsubscribeOn(AndroidSchedulers.mainThread());
            }
            stringSubjectMap.remove(tag);
        }
    }

    public void post(@NonNull BusEvent<T> o) {
        post(o.getClass().getName(), o);
    }

    public void post(@NonNull String tag, @NonNull BusEvent<T> o) {
        for (String key : stringSubjectMap.keySet()) {
            if (tag.equals(key)) {
                stringSubjectMap.get(key).onNext(o);
            }
        }
    }

}
