package com.able.rx.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.able.rx.view.ContentView;
import com.able.rx.view.IBaseView;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static me.imid.swipebacklayout.lib.SwipeBackLayout.EDGE_LEFT;

/**
 * Created by guanlijie on 2018/2/9.
 */

public abstract class BaseActivity extends SwipeBackActivity implements IBaseView {


    private ContentView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSwipeBackLayout().setEdgeTrackingEnabled(EDGE_LEFT);
        contentView = new ContentView(this);
        super.setContentView(contentView);
        contentViewInit(contentView);
    }

    public abstract void contentViewInit(ContentView contentView);

    @Override
    public void setContentView(View view) {
        contentView.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        contentView.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView.setContentView(view, params);
    }

}
