package com.botian.yihu.database;

import org.litepal.crud.DataSupport;

public class UserInfoData extends DataSupport {
    private int noid;
    private String token;
    private String username;
    private String moblie;
    private String avatar;
    private int sex;

    public int getNoid() {
        return noid;
    }

    public void setNoid(int noid) {
        this.noid = noid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
