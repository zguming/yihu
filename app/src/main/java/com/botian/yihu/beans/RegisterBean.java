package com.botian.yihu.beans;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class RegisterBean {

    /**
     * code : 200
     * msg : 注册成功
     * invalidFilter : []
     * data : {"id":7,"token":"91a8af5414d2e7f77a38507d91699784"}
     */

    private int code;
    private String msg;
    private int recode;

    public int getRecode() {
        return recode;
    }

    public void setRecode(int recode) {
        this.recode = recode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
