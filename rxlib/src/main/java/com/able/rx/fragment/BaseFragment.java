package com.able.rx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.able.rx.router.RouterAction;
import com.able.rx.view.ContentView;
import com.able.rx.view.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ZhangJinyu on 2018/2/23.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    private ContentView contentView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = new ContentView(getContext());
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        initArguments(arguments);
        if (arguments == null) {
            initAction(RouterAction.NONE);
        } else {
            initAction(RouterAction.getRouterAction(arguments.getInt("RouterAction", -1)));
        }
        View contentView = getContentView();
        if (contentView != null) {
            this.contentView.setContentView(contentView);
        }
        unbinder = ButterKnife.bind(this, this.contentView);
        contentViewInit(this.contentView);
    }

    public abstract View getContentView();

    public abstract void initArguments(Bundle arguments);

    public abstract void initAction(RouterAction routerAction);

    public abstract void contentViewInit(ContentView contentView);

    public void setContentView(View view) {
        contentView.setContentView(view);
    }

    public void setContentView(int layoutResID) {
        contentView.setContentView(layoutResID);
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView.setContentView(view, params);
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
