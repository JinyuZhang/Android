package com.able.rx.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by guanlijie on 2018/2/10.
 */

public class LifeCycle implements Parcelable {
    private LifeType lifeType;

    public LifeCycle(LifeType lifeType) {
        this.lifeType = lifeType;
    }

    public LifeType getLifeType() {
        return lifeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.lifeType == null ? -1 : this.lifeType.ordinal());
    }

    protected LifeCycle(Parcel in) {
        int tmpLifeType = in.readInt();
        this.lifeType = tmpLifeType == -1 ? null : LifeType.values()[tmpLifeType];
    }

    public static final Parcelable.Creator<LifeCycle> CREATOR = new Parcelable.Creator<LifeCycle>() {
        @Override
        public LifeCycle createFromParcel(Parcel source) {
            return new LifeCycle(source);
        }

        @Override
        public LifeCycle[] newArray(int size) {
            return new LifeCycle[size];
        }
    };
}
