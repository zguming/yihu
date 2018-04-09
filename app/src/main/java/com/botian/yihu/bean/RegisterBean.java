package com.botian.yihu.bean;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class RegisterBean {


    /**
     * code : 1
     * msg : 验证码已发送，请注意查收!
     */

    private int code;
    private String msg;

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
