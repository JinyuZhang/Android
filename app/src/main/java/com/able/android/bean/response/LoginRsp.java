package com.able.android.bean.response;

/**
 * Created by ZhangJinyu on 2018/2/9.
 */

public class LoginRsp {

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 9d710fe37ddb4379882f9918fab7d8ed
         * username : zhangjinyu
         * name : 张金宇
         * gender : 男
         * admin : true
         * birthday : 1990-03-20 00:00:00
         */

        private String id;
        private String username;
        private String name;
        private String gender;
        private boolean admin;
        private String birthday;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
