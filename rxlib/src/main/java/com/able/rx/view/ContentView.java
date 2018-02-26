package com.able.rx.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class ContentView extends LinearLayout {

    private QMUITopBar qmuiTopBar;
    private FrameLayout contentView;
    private QMUIEmptyView qmuiEmptyView;
    private ViewGroup.LayoutParams matchLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private ViewGroup.LayoutParams wrapLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public ContentView(@NonNull Context context) {
        this(context, null);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        contentView = new FrameLayout(context);
        addView(contentView, matchLayoutParams);
        qmuiEmptyView = new QMUIEmptyView(context);
        qmuiEmptyView.setVisibility(GONE);
        qmuiEmptyView.setClickable(true);
        contentView.addView(qmuiEmptyView, matchLayoutParams);
    }

    public QMUITopBar initTopbar() {
        if (qmuiTopBar == null) {
            qmuiTopBar = new QMUITopBar(getContext());
        }
        if (getChildCount() > 0) {
            addView(qmuiTopBar, 0, wrapLayoutParams);
        } else {
            addView(qmuiTopBar, wrapLayoutParams);
        }
        return qmuiTopBar;
    }


    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView.removeView(view);
        if (params != null) {
            contentView.addView(view, 0, params);
        } else {
            contentView.addView(view, 0);
        }
    }

    public void setContentView(View view) {
        setContentView(view, null);
    }

    public void setContentView(@LayoutRes int layoutRes) {
        setContentView(inflate(getContext(), layoutRes, null));
    }

    public QMUITopBar getQmuiTopBar() {
        return qmuiTopBar;
    }

    public QMUIEmptyView getQmuiEmptyView() {
        return qmuiEmptyView;
    }

    public FrameLayout getContentView() {
        return contentView;
    }
}
