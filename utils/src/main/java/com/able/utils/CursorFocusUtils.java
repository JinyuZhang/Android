/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.utils;

import android.widget.EditText;

/**
 * Created by Jinyu Zhang on 2017/8/10.
 */

public class CursorFocusUtils {
    public static void editTextCursorToEnd(EditText editText) {
        editText.setSelection(editText.getText().length());
        editText.requestFocus();
    }
}
