/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.able.rx.R;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

/**
 * Created by Jinyu Zhang on 2017/8/4.
 */

public abstract class AppRouter implements IAppRouter {
    private final ARouter aRouter;
    private Activity context;
    private View view;

    public AppRouter(Activity context) {
        this.context = context;
        aRouter = ARouter.getInstance();
    }

    public void bindView(View view) {
        this.view = view;
    }

    public ARouter getaRouter() {
        return aRouter;
    }

    public String getTitle() {
        return title;
    }

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void navigation() {
        navigation(-1, navCallback);
    }

    public void navigation(int requestCode) {
        navigation(requestCode, this.navCallback);
    }

    public void navigation(int requestCode, NavCallback navCallback) {
        String routerUrl = getRouterPath();
        if (TextUtils.isEmpty(routerUrl)) {
            Logger.d("router url is null.");
            return;
        }
        Postcard postcard = aRouter.build(routerUrl);
        AppPostCard appPostCard = new AppPostCard(postcard);
        withParams(appPostCard);
        String title = getTitle();
        setTitle(title);
        RouterAction routerAction = getRouterAction();
        setRouterAction(routerAction);
        appPostCard.withInt("RouterAction", routerAction.getAction());
        appPostCard.withString("topbarTitle", title == null ? "" : title);
        NavigationAnim navigationAnim = withNavigationAnim(view);
        if (navigationAnim == null) {
            navigationAnim = new NavigationAnim(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        appPostCard.withOptionsCompat(navigationAnim.getActivityOptionsCompat(context));
        if (navCallback == null) {
            if (requestCode == -1) {
                postcard.navigation();
            } else {
                postcard.navigation(context, requestCode);
            }
        } else {
            if (requestCode == -1) {
                postcard.navigation(context, navCallback);
            } else {
                postcard.navigation(context, requestCode, navCallback);
            }
        }
    }

    private RouterAction routerAction;

    public RouterAction getRouterAction() {
        return routerAction == null ? RouterAction.NONE : routerAction;
    }

    public void setRouterAction(RouterAction routerAction) {
        this.routerAction = routerAction;
    }

    @Override
    public void navigation(NavCallback navCallback) {
        if (navCallback == null) {
            this.navCallback = getNavCallback();
        } else {
            this.navCallback = navCallback;
        }
        navigation(-1, this.navCallback);
    }

    private NavCallback navCallback;

    @Override
    public NavCallback getNavCallback() {
        return navCallback;
    }

    @Override
    public NavigationAnim withNavigationAnim(View view) {
        return null;
    }

    @Override
    public void withParams(AppPostCard postCard) {

    }

}
