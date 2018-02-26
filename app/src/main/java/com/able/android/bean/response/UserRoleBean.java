package com.able.android.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haoyaun on 2018/1/26.
 */

public class UserRoleBean implements Parcelable {
    private String name;
    private String des;
    private String sys;
    private String exportAuthority;
    private String id;
    private String DT_RowId;

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getSys() {
        return sys;
    }

    public String getExportAuthority() {
        return exportAuthority;
    }

    public String getId() {
        return id;
    }

    public String getDT_RowId() {
        return DT_RowId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.des);
        dest.writeString(this.sys);
        dest.writeString(this.exportAuthority);
        dest.writeString(this.id);
        dest.writeString(this.DT_RowId);
    }

    public UserRoleBean() {
    }

    protected UserRoleBean(Parcel in) {
        this.name = in.readString();
        this.des = in.readString();
        this.sys = in.readString();
        this.exportAuthority = in.readString();
        this.id = in.readString();
        this.DT_RowId = in.readString();
    }

    public static final Creator<UserRoleBean> CREATOR = new Creator<UserRoleBean>() {
        @Override
        public UserRoleBean createFromParcel(Parcel source) {
            return new UserRoleBean(source);
        }

        @Override
        public UserRoleBean[] newArray(int size) {
            return new UserRoleBean[size];
        }
    };
}
