package com.botian.yihu.beans;

import java.util.List;

public class KaoQianYaTiList {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":28,"pid":0,"name":"押题1","sorts":1,"status":1,"create_time":"2018-08-09 14:40:34","update_time":"2018-08-09 14:45:48","price":"10","mids":2},{"id":29,"pid":28,"name":"分类一","sorts":2,"status":1,"create_time":"2018-08-09 14:40:51","update_time":"2018-08-09 14:45:51","price":"","mids":2},{"id":30,"pid":28,"name":"分类二","sorts":3,"status":1,"create_time":"2018-08-09 14:41:02","update_time":"2018-08-09 14:45:53","price":"","mids":2}]
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
         * id : 28
         * pid : 0
         * name : 押题1
         * sorts : 1
         * status : 1
         * create_time : 2018-08-09 14:40:34
         * update_time : 2018-08-09 14:45:48
         * price : 10
         * mids : 2
         */

        private int id;
        private int pid;
        private String name;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private String price;
        private int mids;
        private int buy;//1为购买过,0为未购买

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getMids() {
            return mids;
        }

        public void setMids(int mids) {
            this.mids = mids;
        }
    }
}
