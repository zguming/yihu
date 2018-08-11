package com.botian.yihu.beans;

import java.util.List;

public class MoniBuy {

    /**
     * code : 200
     * msg : 购买成功
     * invalidFilter : []
     * data : {"id":91,"userid":14,"column_id":2,"cbt_id":1,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00"}
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
         * id : 91
         * userid : 14
         * column_id : 2
         * cbt_id : 1
         * create_time : 1970-01-01 08:00:00
         * update_time : 1970-01-01 08:00:00
         */

        private int id;
        private int userid;
        private int column_id;
        private int cbt_id;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public int getCbt_id() {
            return cbt_id;
        }

        public void setCbt_id(int cbt_id) {
            this.cbt_id = cbt_id;
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
