package com.able.rx.view;

import com.able.rx.bean.LoadingType;
import com.able.rx.bean.TipType;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public interface ILoadingView extends IBaseView {
    void showLoading(String tag, LoadingType loadingType, String titleText, String detailText);

    void showMsg(TipType tipType, String titleText, String msg);

    void showMsg(String titleText, String buttonText, String msg, ClickCallback clickCallback);

    void dismiss(String tag, LoadingType loadingType);

    void dismissAllLoading(LoadingType loadingType);

    void dismissAllLoading();
}
