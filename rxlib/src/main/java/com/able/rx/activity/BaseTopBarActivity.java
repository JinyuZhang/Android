package com.able.rx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.widget.TextView;

import com.able.rx.R;
import com.able.rx.router.RouterAction;
import com.able.rx.view.ContentView;
import com.able.utils.ResourceUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public abstract class BaseTopBarActivity extends BaseActivity {

    private QMUITopBar qmuiTopBar;
    private String topbarTitle;
    private int routerActionInt;
    protected RouterAction routerAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qmuiTopBar = getContentView().initTopbar();
        qmuiTopBar.setBackgroundResource(R.color.colorPrimaryDark);
    }

    @CallSuper
    @Override
    public void initArguments(Intent intent) {
        topbarTitle = intent.getStringExtra("topbarTitle");
        routerActionInt = intent.getIntExtra("RouterAction", -1);
    }

    @CallSuper
    @Override
    public void initAction(RouterAction routerAction) {
        this.routerAction = routerAction;
    }

    @CallSuper
    @Override
    public void contentViewInit(ContentView contentView) {
        TextView textView = setTopbarTitle(topbarTitle);
        textView.setTextColor(ResourceUtil.getColor(R.color.colorContent));
        initAction(RouterAction.getRouterAction(routerActionInt));
    }

    public QMUITopBar getQmuiTopBar() {
        return qmuiTopBar;
    }

    public TextView setTopbarTitle(String title) {
        return qmuiTopBar.setTitle(title);
    }

    public String getTopbarTitle() {
        return qmuiTopBar.getTitle().toString();
    }
}
