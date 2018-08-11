package com.botian.yihu.beans;

public class KaoQianBuy {

    /**
     * code : 200
     * msg : 购买成功
     * data : {"id":148,"userid":14,"column_id":2,"bet_id":28,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * id : 148
         * userid : 14
         * column_id : 2
         * bet_id : 28
         * create_time : 1970-01-01 08:00:00
         * update_time : 1970-01-01 08:00:00
         */

        private int id;
        private int userid;
        private int column_id;
        private int bet_id;
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

        public int getBet_id() {
            return bet_id;
        }

        public void setBet_id(int bet_id) {
            this.bet_id = bet_id;
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
