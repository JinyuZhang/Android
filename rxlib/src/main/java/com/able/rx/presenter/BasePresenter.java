package com.able.rx.presenter;

import android.support.annotation.CallSuper;

import com.able.rx.bean.LifeType;
import com.able.rx.tools.event.BusEvent;
import com.able.rx.tools.event.RxBus;
import com.able.rx.view.IBaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class BasePresenter<V extends IBaseView> {
    protected V contentView;
    private final String classTag;

    public BasePresenter(@NonNull V view) {
        this.contentView = view;
        classTag = contentView.getClassName();
        RxBus.getInstance().register(classTag, cycleConsumer);
    }

    private Consumer<BusEvent<LifeType>> cycleConsumer = new Consumer<BusEvent<LifeType>>() {
        @Override
        public void accept(BusEvent<LifeType> busEvent) throws Exception {
            switch (busEvent.getData()) {
                case ON_CREATE:
                    break;
                case ON_RESUME:
                    break;
                case ON_STOP:
                    break;
                case ON_DESTROY:
                    onDestroy();
                    break;
            }
        }
    };

    @CallSuper
    public void onDestroy() {
        RxBus.getInstance().unRegister(classTag);
        contentView = null;
    }

    public abstract String getTag();
}
