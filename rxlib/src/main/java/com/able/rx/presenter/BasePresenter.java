package com.able.rx.presenter;

import android.support.annotation.CallSuper;

import com.able.rx.bean.LifeCycle;
import com.able.rx.tools.event.RxBus;
import com.able.rx.view.IBaseView;

import able.com.debug.logger.Logger;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class BasePresenter<V extends IBaseView> {
    private V contentView;
    private final String classTag;

    public BasePresenter(@NonNull V view) {
        this.contentView = view;
        classTag = contentView.getClassName();
        RxBus.getInstance().register(classTag, LifeCycle.class, cycleConsumer);
    }

    private Consumer<LifeCycle> cycleConsumer = new Consumer<LifeCycle>() {
        @Override
        public void accept(LifeCycle lifeCycle) throws Exception {
            switch (lifeCycle.getLifeType()) {
                case ON_CREATE:
                    Logger.d("onCreate");
                    break;
                case ON_RESUME:
                    Logger.d("onResume");
                    break;
                case ON_STOP:
                    Logger.d("onStop");
                    break;
                case ON_DESTROY:
                    Logger.d("onDestroy");
                    onDestroy();
                    break;
            }
        }
    };

    @CallSuper
    public void onDestroy() {
        RxBus.getInstance().unRegister(classTag);
    }
}
