/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.utils;

import android.view.View;

/**
 * ResourceUtil
 */
public class ResourceUtil {

    private ResourceUtil() {
        throw new AssertionError();
    }

    /**
     * 获取strings.xml中stringId对应的有序字符集合
     *
     * @param stringId
     * @return String
     */
    public static String getString(int stringId) {
        return Utils.getContext().getResources().getString(stringId).toString();
    }

    /**
     * 获取strings.xml中stringId对应的有序字符数组
     *
     * @param stringId
     * @return
     */
    public static String[] getStrings(int stringId) {
        return Utils.getContext().getResources().getStringArray(stringId);
    }

    /**
     * 获取colors.xml中colorId对应的color int值
     *
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        return Utils.getContext().getResources().getColor(colorId);
    }

    /**
     * 获取dimen.xml中dimenId对应的dimen int值
     *
     * @param dimenId
     * @return
     */
    public static float getDimenFloat(int dimenId) {
        return Utils.getContext().getResources().getDimension(dimenId);
    }

    /**
     * 获取dimen.xml中dimenId对应的dimen int值
     *
     * @param dimenId
     * @return
     */
    public static int getDimenInt(int dimenId) {
        return (int) Utils.getContext().getResources().getDimension(dimenId);
    }

    /**
     * 获取dimen.xml中dimenId对应的dimen int值
     *
     * @param integerId
     * @return
     */
    public static int getInteger(int integerId) {
        return Utils.getContext().getResources().getInteger(integerId);
    }

    /**
     * 获取layout下的layout布局
     *
     * @param layout
     * @return View
     */
    public static View getLayout(int layout) {
        return View.inflate(Utils.getContext(), layout, null);
    }
}
