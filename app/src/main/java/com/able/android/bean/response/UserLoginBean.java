package com.able.android.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.able.android.bean.base.NoneRspBean;


/**
 * Created by haoyaun on 2018/1/26.
 */

public class UserLoginBean extends NoneRspBean {
    private String usertype;
    private String username;
    private String password;
    private UserRoleBean userRole;
    private String name;
    private String sys;
    private String sex;
    private String userState;
    private String delSign;
    private String createTime;
    private String deviceId;
    private String orgno;
    private String manager;
    private String id;
    private String DT_RowId;

    public String getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRoleBean getUserRole() {
        return userRole;
    }

    public String getName() {
        return name;
    }

    public String getSys() {
        return sys;
    }

    public String getSex() {
        return sex;
    }

    public String getUserState() {
        return userState;
    }

    public String getDelSign() {
        return delSign;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getOrgno() {
        return orgno;
    }

    public String getManager() {
        return manager;
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
        dest.writeString(this.usertype);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeParcelable(this.userRole, flags);
        dest.writeString(this.name);
        dest.writeString(this.sys);
        dest.writeString(this.sex);
        dest.writeString(this.userState);
        dest.writeString(this.delSign);
        dest.writeString(this.createTime);
        dest.writeString(this.deviceId);
        dest.writeString(this.orgno);
        dest.writeString(this.manager);
        dest.writeString(this.id);
        dest.writeString(this.DT_RowId);
    }

    public UserLoginBean() {
    }

    protected UserLoginBean(Parcel in) {
        this.usertype = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.userRole = in.readParcelable(UserRoleBean.class.getClassLoader());
        this.name = in.readString();
        this.sys = in.readString();
        this.sex = in.readString();
        this.userState = in.readString();
        this.delSign = in.readString();
        this.createTime = in.readString();
        this.deviceId = in.readString();
        this.orgno = in.readString();
        this.manager = in.readString();
        this.id = in.readString();
        this.DT_RowId = in.readString();
    }

    public static final Parcelable.Creator<UserLoginBean> CREATOR = new Parcelable.Creator<UserLoginBean>() {
        @Override
        public UserLoginBean createFromParcel(Parcel source) {
            return new UserLoginBean(source);
        }

        @Override
        public UserLoginBean[] newArray(int size) {
            return new UserLoginBean[size];
        }
    };
}
