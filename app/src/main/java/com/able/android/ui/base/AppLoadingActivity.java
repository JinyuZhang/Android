package com.able.android.ui.base;

import android.view.View;

import com.able.android.R;
import com.able.rx.activity.BaseLoadingActivity;
import com.able.rx.view.ContentView;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;

/**
 * Created by ZhangJinyu on 2018/2/24.
 */

public class AppLoadingActivity extends BaseLoadingActivity {

    private QMUIAlphaImageButton qmuiAlphaImageButton;

    @Override
    public void contentViewInit(ContentView contentView) {
        super.contentViewInit(contentView);
        qmuiAlphaImageButton = getQmuiTopBar().addLeftImageButton(R.drawable.ic_back, R.id.ic_back);
        qmuiAlphaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setBackViewVisiable(boolean isVisiable) {
        qmuiAlphaImageButton.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
    }
}
