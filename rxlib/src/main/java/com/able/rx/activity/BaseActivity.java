package com.able.rx.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.able.rx.bean.LifeCycle;
import com.able.rx.bean.LifeType;
import com.able.rx.router.RouterAction;
import com.able.rx.tools.event.RxBus;
import com.able.rx.tools.timer.RxTimer;
import com.able.rx.view.ContentView;
import com.able.rx.view.IBaseView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static me.imid.swipebacklayout.lib.SwipeBackLayout.EDGE_LEFT;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public abstract class BaseActivity extends SwipeBackActivity implements IBaseView {

    private ContentView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSwipeBackLayout().setEdgeTrackingEnabled(EDGE_LEFT);
        contentView = new ContentView(this);
        super.setContentView(contentView);
        initArguments(getIntent());
    }

    public abstract void initArguments(Intent intent);

    public abstract void initAction(RouterAction routerAction);

    public abstract void contentViewInit(ContentView contentView);

    @Override
    public void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView.setContentView(view, params);
        ButterKnife.bind(this);
        contentViewInit(contentView);
    }

    public ContentView getContentView() {
        return contentView;
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    private RxTimer rxTimer;

    public void postDelayed(Runnable runnable, int delayMillis) {
        if (rxTimer == null) {
            rxTimer = new RxTimer();
        }
        rxTimer.postDelayed(delayMillis, TimeUnit.MILLISECONDS, runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxBus.getInstance().post(getClassName(), new LifeCycle(LifeType.ON_STOP));
        RxBus.getInstance().post(getClassName(), new LifeCycle(LifeType.ON_DESTROY));
        if (rxTimer != null) {
            rxTimer.onDestroy();
        }
    }

}
