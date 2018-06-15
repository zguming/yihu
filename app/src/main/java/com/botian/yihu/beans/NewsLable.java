package com.botian.yihu.beans;

import java.util.List;

public class NewsLable {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"pid":0,"typename":"测试","mid":1,"litpic":"","sorts":0,"status":1,"create_time":"2018-05-28 09:17:34","update_time":"2018-05-28 09:17:34"},{"id":2,"pid":0,"typename":"测试2","mid":1,"litpic":"","sorts":0,"status":1,"create_time":"2018-05-30 14:58:11","update_time":"2018-05-30 14:58:11"},{"id":3,"pid":0,"typename":"测试3","mid":1,"litpic":"","sorts":0,"status":1,"create_time":"2018-05-30 14:58:16","update_time":"2018-05-30 14:58:16"}]
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
         * typename : 测试
         * mid : 1
         * litpic :
         * sorts : 0
         * status : 1
         * create_time : 2018-05-28 09:17:34
         * update_time : 2018-05-28 09:17:34
         */

        private int id;
        private int pid;
        private String typename;
        private int mid;
        private String litpic;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;

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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
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
    }
}
