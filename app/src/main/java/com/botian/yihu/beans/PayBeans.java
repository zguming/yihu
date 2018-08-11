package com.botian.yihu.beans;

import java.util.List;

public class PayBeans {

    /**
     * code : 200
     * msg : 提交成功
     * invalidFilter : []
     * data : {"id":42,"userid":14,"create_time":"2018-07-30 09:51:48","update_time":"2018-07-30 09:51:48","order":"110","account":"15113993657","money":"100.00","payment":1}
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
         * id : 42
         * userid : 14
         * create_time : 2018-07-30 09:51:48
         * update_time : 2018-07-30 09:51:48
         * order : 110
         * account : 15113993657
         * money : 100.00
         * payment : 1
         */

        private int id;
        private int userid;
        private String create_time;
        private String update_time;
        private String order;
        private String account;
        private String money;
        private int payment;

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

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }
    }
}
