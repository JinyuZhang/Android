package com.able.rx.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.widget.TextView;

import com.able.rx.R;
import com.able.rx.router.RouterAction;
import com.able.rx.view.ContentView;
import com.able.utils.ResourceUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by haoyaun on 2018/1/18.
 */

public abstract class BaseTopbarFragment extends BaseFragment {
    private QMUITopBar qmuiTopBar;
    protected RouterAction routerAction;


    @Override
    public void initArguments(Bundle arguments) {

    }

    @CallSuper
    @Override
    public void initAction(RouterAction routerAction) {
        this.routerAction = routerAction;
    }

    @CallSuper
    @Override
    public void contentViewInit(ContentView contentView) {
        qmuiTopBar = contentView.initTopbar();
        qmuiTopBar.setBackgroundResource(R.color.colorPrimary);
    }

    public void setTitle(String topbarTitle) {
        TextView textView = setTopbarTitle(topbarTitle);
        textView.setTextColor(ResourceUtil.getColor(R.color.colorContent));
    }

    public QMUITopBar getQmuiTopBar() {
        return qmuiTopBar;
    }

    public TextView setTopbarTitle(String topbarTitle) {
        return qmuiTopBar.setTitle(topbarTitle);
    }

    public String getTopbarTitle() {
        return qmuiTopBar.getTitle().toString();
    }

}
