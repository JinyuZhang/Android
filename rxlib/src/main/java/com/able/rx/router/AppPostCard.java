/*
 * Copyright (c) 2018.
 * 作者:张金宇
 * 个人著作 版权所有
 * Zhang Jinyu,All Rights Reserved
 */

package com.able.rx.router;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.Postcard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jinyu Zhang on 2017/8/4.
 */

public class AppPostCard {
    private Postcard postcard;

    public AppPostCard(Postcard postcard) {
        this.postcard = postcard;

    }

    public void with(Bundle bundle) {
        postcard.with(bundle);
    }

    public void withFlags(@Postcard.FlagInt int flag) {
        postcard.withFlags(flag);
    }

    public void withObject(@Nullable String key, @Nullable Object value) {
        postcard.withObject(key, value);
    }

    public void withString(@Nullable String key, @Nullable String value) {
        postcard.withString(key, value);
    }

    public void withBoolean(@Nullable String key, boolean value) {
        postcard.withBoolean(key, value);
    }

    public void withShort(@Nullable String key, short value) {
        postcard.withShort(key, value);
    }

    public void withInt(@Nullable String key, int value) {
        postcard.withInt(key, value);
    }

    public void withLong(@Nullable String key, long value) {
        postcard.withLong(key, value);
    }

    public void withDouble(@Nullable String key, double value) {
        postcard.withDouble(key, value);
    }

    public void withByte(@Nullable String key, byte value) {
        postcard.withByte(key, value);
    }

    public void withChar(@Nullable String key, char value) {
        postcard.withChar(key, value);
    }

    public void withFloat(@Nullable String key, float value) {
        postcard.withFloat(key, value);
    }

    public void withCharSequence(@Nullable String key, @Nullable CharSequence value) {
        postcard.withCharSequence(key, value);
    }

    public void withParcelable(@Nullable String key, @Nullable Parcelable value) {
        postcard.withParcelable(key, value);
    }

    public void withParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        postcard.withParcelableArray(key, value);
    }

    public void withParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends Parcelable> value) {
        postcard.withParcelableArrayList(key, value);
    }

    public void withSparseParcelableArray(@Nullable String key, @Nullable SparseArray<? extends Parcelable> value) {
        postcard.withSparseParcelableArray(key, value);
    }

    public void withIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        postcard.withIntegerArrayList(key, value);
    }

    public void withStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        postcard.withStringArrayList(key, value);
    }

    public void withCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        postcard.withCharSequenceArrayList(key, value);
    }

    public void withSerializable(@Nullable String key, @Nullable Serializable value) {
        postcard.withSerializable(key, value);
    }

    public void withByteArray(@Nullable String key, @Nullable byte[] value) {
        postcard.withByteArray(key, value);
    }

    public void withShortArray(@Nullable String key, @Nullable short[] value) {
        postcard.withShortArray(key, value);
    }

    public void withCharArray(@Nullable String key, @Nullable char[] value) {
        postcard.withCharArray(key, value);
    }

    public void withFloatArray(@Nullable String key, @Nullable float[] value) {
        postcard.withFloatArray(key, value);
    }

    public void withCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        postcard.withCharSequenceArray(key, value);
    }

    public void withBundle(@Nullable String key, @Nullable Bundle value) {
        postcard.withBundle(key, value);
    }

    public void withTransition(int enterAnim, int exitAnim) {
        postcard.withTransition(enterAnim, exitAnim);
    }

    @RequiresApi(16)
    public void withOptionsCompat(ActivityOptionsCompat compat) {
        postcard.withOptionsCompat(compat);
    }
}
