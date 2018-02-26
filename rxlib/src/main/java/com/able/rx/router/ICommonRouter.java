/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;

import com.alibaba.android.arouter.facade.callback.NavCallback;

/**
 * Created by Jinyu Zhang on 2017/8/4.
 */

interface ICommonRouter {
    /**
     * 导航回调
     *
     * @return
     */
    NavCallback getNavCallback();

    /**
     * 获取路由路径
     *
     * @return
     */
    String getRouterPath();
}
