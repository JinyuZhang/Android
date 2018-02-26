package com.able.rx.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.able.rx.R;


/**
 * Created by ZhangJinyu on 2017-09-07.
 */

public class BottomDialog extends Dialog {
    public BottomDialog(@NonNull Context context) {
        super(context, R.style.BottomDialog);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null));
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getContext().getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void show() {
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        super.show();
    }
}
