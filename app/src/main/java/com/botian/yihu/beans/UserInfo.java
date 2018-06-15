package com.botian.yihu.beans;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = -56877674093324430L;
    /**
     * id : 12
     * token : 1d0f7d2e68f24ad9ccb19106461f91dc
     * username : 我？♥✔
     * moblie : 15113993657
     */

    private int id;
    private String token;
    private String username;
    private String moblie;
    private String avatar;
    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
