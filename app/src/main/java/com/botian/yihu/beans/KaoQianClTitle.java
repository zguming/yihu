package com.botian.yihu.beans;

import java.util.List;

public class KaoQianClTitle {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":24,"bet_id":29,"mids":2,"title":"分析题","status":1,"create_time":"1970-01-01 08:00:00","update_time":"2018-08-09 14:53:11"}]
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
         * id : 24
         * bet_id : 29
         * mids : 2
         * title : 分析题
         * status : 1
         * create_time : 1970-01-01 08:00:00
         * update_time : 2018-08-09 14:53:11
         */

        private int id;
        private int bet_id;
        private int mids;
        private String title;
        private int status;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBet_id() {
            return bet_id;
        }

        public void setBet_id(int bet_id) {
            this.bet_id = bet_id;
        }

        public int getMids() {
            return mids;
        }

        public void setMids(int mids) {
            this.mids = mids;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
