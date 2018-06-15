package com.botian.yihu.beans;

public class UploadPhoto {


    /**
     * code : 200
     * msg : 图片上传成功!
     * data : /uploads/users/20180529/224d86ed43a8f015a8ecd0f7c59ee53c.png
     */

    private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
