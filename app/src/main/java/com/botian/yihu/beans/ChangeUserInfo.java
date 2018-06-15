package com.botian.yihu.beans;

import java.util.List;

public class ChangeUserInfo {

    /**
     * code : 200
     * msg : 修改成功
     * invalidFilter : []
     * data : []
     */

    private int code;
    private String msg;
    private List<?> invalidFilter;
    private List<?> data;

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

    public List<?> getInvalidFilter() {
        return invalidFilter;
    }

    public void setInvalidFilter(List<?> invalidFilter) {
        this.invalidFilter = invalidFilter;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
