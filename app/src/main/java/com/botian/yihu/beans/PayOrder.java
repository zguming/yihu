package com.botian.yihu.beans;

import java.util.List;

public class PayOrder {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":39,"userid":14,"create_time":"2018-07-28 10:06:20","update_time":"2018-07-28 10:06:20","order":"111","account":"11","money":"10.00","payment":1},{"id":40,"userid":14,"create_time":"2018-07-28 10:06:44","update_time":"2018-07-28 10:06:44","order":"111","account":"11","money":"10.00","payment":1},{"id":41,"userid":14,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","order":"1111","account":"111","money":"10.00","payment":1},{"id":42,"userid":14,"create_time":"2018-07-30 09:51:48","update_time":"2018-07-30 09:51:48","order":"110","account":"15113993657","money":"100.00","payment":1}]
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
         * id : 39
         * userid : 14
         * create_time : 2018-07-28 10:06:20
         * update_time : 2018-07-28 10:06:20
         * order : 111
         * account : 11
         * money : 10.00
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
