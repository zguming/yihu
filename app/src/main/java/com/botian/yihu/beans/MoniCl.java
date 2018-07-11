package com.botian.yihu.beans;

import java.util.List;

public class MoniCl {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"typeid":1,"mid":1,"title":"测试","status":1,"create_time":"2018-06-06 15:59:20","update_time":"2018-06-06 15:59:27"},{"id":2,"typeid":2,"mid":2,"title":"测试2","status":1,"create_time":"2018-06-07 08:43:07","update_time":"2018-06-07 08:43:14"}]
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
         * typeid : 1
         * mid : 1
         * title : 测试
         * status : 1
         * create_time : 2018-06-06 15:59:20
         * update_time : 2018-06-06 15:59:27
         */

        private int id;
        private int typeid;
        private int mid;
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

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
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
