/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v4.app.ActivityOptionsCompat;

/**
 * Created by Jinyu Zhang on 2017/8/7.
 */

public class NavigationAnim {
    private int enterAnim;
    private int exitAnim;
    private ActivityOptionsCompat activityOptionsCompat;

    public NavigationAnim(@AnimRes int enterAnim, @AnimRes int exitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
    }

    public NavigationAnim(ActivityOptionsCompat activityOptionsCompat) {
        this.activityOptionsCompat = activityOptionsCompat;
    }

    public ActivityOptionsCompat getActivityOptionsCompat(Context context) {
        if (activityOptionsCompat == null) {
            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim);
        }
        return activityOptionsCompat;
    }

}
