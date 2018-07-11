package com.botian.yihu.beans;

import java.util.List;

public class MistakeBean {


    /**
     * code : 200
     * msg : 提交成功
     * invalidFilter : []
     * data : {"id":58,"uid":14,"content":"方大婶","status":0,"create_time":"2018-07-02 14:49:17","update_time":"2018-07-02 14:49:17"}
     */

    private int code;
    private String msg;
    private DataBean data;
    private List<?> invalidFilter;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getInvalidFilter() {
        return invalidFilter;
    }

    public void setInvalidFilter(List<?> invalidFilter) {
        this.invalidFilter = invalidFilter;
    }

    public static class DataBean {
        /**
         * id : 58
         * uid : 14
         * content : 方大婶
         * status : 0
         * create_time : 2018-07-02 14:49:17
         * update_time : 2018-07-02 14:49:17
         */

        private int id;
        private int uid;
        private String content;
        private int status;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
