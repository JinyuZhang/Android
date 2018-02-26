/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;


import android.view.View;

import com.alibaba.android.arouter.facade.callback.NavCallback;

/**
 * Created by Jinyu Zhang on 2017/8/4.
 */

interface IAppRouter extends ICommonRouter {

    void withParams(AppPostCard postcard);

    NavigationAnim withNavigationAnim(View view);

    void navigation();

    void navigation(NavCallback navCallback);
}
