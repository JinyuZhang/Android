package com.able.rx.activity;


import android.text.TextUtils;
import android.view.View;

import com.able.rx.R;
import com.able.rx.bean.LoadingType;
import com.able.rx.bean.TipType;
import com.able.rx.view.ClickCallback;
import com.able.rx.view.ContentView;
import com.able.rx.view.ILoadingView;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.HashMap;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class BaseLoadingActivity extends BaseActivity implements ILoadingView {
    private QMUIEmptyView qmuiEmptyView;
    private HashMap<String, LoadingType> contentLoadingTags = new HashMap<>();
    private HashMap<String, LoadingType> dialogLoadingTags = new HashMap<>();
    private QMUITipDialog.Builder tipDialogBuilder;
    private QMUITipDialog qmuiLoadingDialog;
    private QMUITipDialog qmuiTipDialog;

    @Override

    public void contentViewInit(ContentView contentView) {
        contentView.initTopbar();
        qmuiEmptyView = contentView.getQmuiEmptyView();
        qmuiEmptyView.setBackgroundResource(R.color.colorAccent);
    }

    public QMUIEmptyView getQmuiEmptyView() {
        return qmuiEmptyView;
    }

    private void initQmuiDialogBuilder() {
        if (tipDialogBuilder == null) {
            tipDialogBuilder = new QMUITipDialog.Builder(this);
        }
    }

    private void resetLoadingDialogStatus() {
        if (qmuiLoadingDialog != null && qmuiLoadingDialog.isShowing()) {
            qmuiLoadingDialog.dismiss();
        }
    }

    private void resetTipDialogStatus() {
        if (qmuiTipDialog != null && qmuiTipDialog.isShowing()) {
            qmuiTipDialog.dismiss();
        }
    }

    @Override
    public void showLoading(String tag, LoadingType loadingType, String titleText, String detailText) {
        switch (loadingType) {
            case CONTENT:
                contentLoadingTags.put(tag, loadingType);
                qmuiEmptyView.show(titleText, detailText);
                qmuiEmptyView.setLoadingShowing(true);
                break;
            case DIALOG:
                dialogLoadingTags.put(tag, loadingType);
                initQmuiDialogBuilder();
                resetLoadingDialogStatus();
                qmuiLoadingDialog = tipDialogBuilder.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(TextUtils.isEmpty(titleText) ? "正在加载" : titleText)
                        .create();
                qmuiLoadingDialog.show();
                break;
        }
    }

    @Override
    public void showMsg(TipType tipType, String titleText, String msg) {
        if (tipType.equals(TipType.CONTENT)) {
            qmuiEmptyView.show(titleText, msg);
        } else {
            initQmuiDialogBuilder();
            resetTipDialogStatus();
            qmuiTipDialog.show();
            switch (tipType) {
                case INFO:
                    tipDialogBuilder.setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO);
                    break;
                case SUCCESS:
                    tipDialogBuilder.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS);
                    break;
                case FAIL:
                    tipDialogBuilder.setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL);
                    break;
            }
            qmuiTipDialog = tipDialogBuilder.setTipWord(titleText).create();
            qmuiTipDialog.show();
        }
    }

    @Override
    public void showMsg(String titleText, String buttonText, String msg, final ClickCallback clickCallback) {
        qmuiEmptyView.show(false, titleText, buttonText, msg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCallback != null) {
                    clickCallback.clickCall();
                }
            }
        });
    }


    @Override
    public void dismiss(String tag, LoadingType loadingType) {
        switch (loadingType) {
            case CONTENT:
                if (contentLoadingTags.isEmpty()) {
                    return;
                }
                contentLoadingTags.remove(tag);
                checkContentLoadingStatus();
                break;
            case DIALOG:
                if (dialogLoadingTags.isEmpty()) {
                    return;
                }
                dialogLoadingTags.remove(tag);
                checkDialogLoadingStatus();
                break;
        }
    }

    private void checkContentLoadingStatus() {
        if (contentLoadingTags.isEmpty() && qmuiEmptyView != null) {
            qmuiEmptyView.setLoadingShowing(false);
            qmuiEmptyView.hide();
        }
    }

    private void checkDialogLoadingStatus() {
        if (dialogLoadingTags.isEmpty() && qmuiLoadingDialog != null) {
            qmuiLoadingDialog.dismiss();
        }
    }

    @Override
    public void dismissAllLoading(LoadingType loadingType) {
        switch (loadingType) {
            case CONTENT:
                contentLoadingTags.clear();
                checkContentLoadingStatus();
                break;
            case DIALOG:
                dialogLoadingTags.clear();
                checkDialogLoadingStatus();
                break;
        }
    }

    @Override
    public void dismissAllLoading() {
        contentLoadingTags.clear();
        checkContentLoadingStatus();
        dialogLoadingTags.clear();
        checkDialogLoadingStatus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissAllLoading();
        if (qmuiTipDialog != null) {
            qmuiTipDialog.dismiss();
        }
    }
}
