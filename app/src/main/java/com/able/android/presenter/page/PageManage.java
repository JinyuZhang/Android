/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.android.presenter.page;

/**
 * Created by ZhangJinyu on 2018/1/5.
 */

public class PageManage {
    private boolean isFrist = true;
    private final int pageNumMinDefault = 20;
    private final int pageNumMaxDefault = 100;
    private int page;
    private int pageNum;

    public PageManage() {
        this.pageNum = pageNumMinDefault;
    }

    public PageManage(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = pageNumMinDefault;
        } else {
            if (this.pageNum > pageNumMaxDefault) {
                this.pageNum = pageNumMaxDefault;
            } else {
                this.pageNum = pageNum;
            }
        }
    }

    private boolean refresh;

    public void setRefresh(boolean isRefresh) {
        this.refresh = isRefresh;
        if (refresh) {
            page = 1;
        } else {
            page++;
        }
    }

    public int getPage() {
        return page;
    }

    public int getPageNum() {
        return pageNum;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public boolean isFrist() {
        return isFrist;
    }

    public boolean isHasNextPage(int totalCount) {
        if (totalCount == 0) {
            return false;
        }
        return totalCount > page * pageNum;
    }

    public void successful() {
        isFrist = false;
    }

    public void fail() {
        if (page != 1) {
            page--;
        }
    }
}
