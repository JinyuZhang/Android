/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;


/**
 * Created by ZhangJinyu on 2018/1/6.
 */

public enum RouterAction {
    NONE(-1), LOOK(0), CREATE(1), UPDATE(2);
    private int action;

    private RouterAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public static RouterAction getRouterAction(int action) {
        switch (action) {
            case 0:
                return LOOK;
            case 1:
                return CREATE;
            case 2:
                return UPDATE;
            default:
                return NONE;
        }
    }
}
