package com.botian.yihu.beans;

import java.util.List;

public class ShareList {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":379,"chaprerid":2,"userid":14,"create_time":"2018-07-24 10:54:49","update_time":"2018-07-24 10:54:49","columnid":2},{"id":380,"chaprerid":1,"userid":14,"create_time":"2018-07-24 11:30:51","update_time":"2018-07-24 11:30:51","columnid":2},{"id":381,"chaprerid":5,"userid":14,"create_time":"2018-07-24 11:38:05","update_time":"2018-07-24 11:38:05","columnid":2},{"id":382,"chaprerid":6,"userid":14,"create_time":"2018-07-24 11:42:50","update_time":"2018-07-24 11:42:50","columnid":2},{"id":383,"chaprerid":8,"userid":14,"create_time":"2018-07-24 11:43:33","update_time":"2018-07-24 11:43:33","columnid":2}]
     */

    private int code;
    private String msg;
    private List<?> invalidFilter;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 379
         * chaprerid : 2
         * userid : 14
         * create_time : 2018-07-24 10:54:49
         * update_time : 2018-07-24 10:54:49
         * columnid : 2
         */

        private int id;
        private int chaprerid;
        private int userid;
        private String create_time;
        private String update_time;
        private int columnid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChaprerid() {
            return chaprerid;
        }

        public void setChaprerid(int chaprerid) {
            this.chaprerid = chaprerid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public int getColumnid() {
            return columnid;
        }

        public void setColumnid(int columnid) {
            this.columnid = columnid;
        }
    }
}
