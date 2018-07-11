package com.botian.yihu.beans;

import java.util.List;

public class MoniTest {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"pid":0,"typename":"考场一","mid":1,"sorts":0,"status":1,"create_time":"2018-06-06 09:09:18","update_time":"2018-06-06 09:19:01","share":0,"money":"10"}]
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
         * id : 1
         * pid : 0
         * typename : 考场一
         * mid : 1
         * sorts : 0
         * status : 1
         * create_time : 2018-06-06 09:09:18
         * update_time : 2018-06-06 09:19:01
         * share : 0
         * money : 10
         */

        private int id;
        private int pid;
        private String typename;
        private int mid;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private int share;
        private String money;

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

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
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

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
