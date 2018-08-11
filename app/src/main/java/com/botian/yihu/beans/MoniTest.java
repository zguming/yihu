package com.botian.yihu.beans;

import java.util.List;

public class MoniTest {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"pid":0,"typename":"考场一","mid":1,"mids":2,"sorts":0,"status":1,"create_time":"2018-06-06 09:09:18","update_time":"2018-07-16 15:32:27","share":0,"money":"10","examination":"120","fartion":"380"},{"id":2,"pid":0,"typename":"考场二","mid":1,"mids":2,"sorts":0,"status":1,"create_time":"2018-06-06 10:13:45","update_time":"2018-07-16 15:29:26","share":0,"money":"10","examination":"120","fartion":"380"},{"id":3,"pid":0,"typename":"护师1","mid":1,"mids":3,"sorts":1,"status":1,"create_time":"2018-08-11 11:21:39","update_time":"2018-08-11 11:25:25","share":0,"money":"10","examination":"200","fartion":"200"},{"id":4,"pid":0,"typename":"护师2","mid":1,"mids":3,"sorts":0,"status":1,"create_time":"2018-08-11 11:22:07","update_time":"2018-08-11 11:22:07","share":0,"money":"20","examination":"200","fartion":"200"}]
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
         * mids : 2
         * sorts : 0
         * status : 1
         * create_time : 2018-06-06 09:09:18
         * update_time : 2018-07-16 15:32:27
         * share : 0
         * money : 10
         * examination : 120
         * fartion : 380
         */

        private int id;
        private int pid;
        private String typename;
        private int mid;
        private int mids;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private int share;
        private String money;
        private String examination;
        private String fartion;

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

        public int getMids() {
            return mids;
        }

        public void setMids(int mids) {
            this.mids = mids;
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

        public String getExamination() {
            return examination;
        }

        public void setExamination(String examination) {
            this.examination = examination;
        }

        public String getFartion() {
            return fartion;
        }

        public void setFartion(String fartion) {
            this.fartion = fartion;
        }
    }
}
