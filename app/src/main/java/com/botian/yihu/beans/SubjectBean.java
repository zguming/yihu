package com.botian.yihu.beans;

import java.util.List;

public class SubjectBean {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"pid":0,"name":"医护类","sorts":1,"status":1,"create_time":"2018-07-09 17:15:45","update_time":"2018-07-09 17:15:45"},{"id":2,"pid":1,"name":"护士职业","sorts":1,"status":1,"create_time":"2018-07-09 17:16:01","update_time":"2018-07-09 17:16:48"},{"id":3,"pid":1,"name":"护师职业","sorts":2,"status":1,"create_time":"2018-07-09 17:16:13","update_time":"2018-07-09 17:23:22"},{"id":4,"pid":1,"name":"主管护师","sorts":3,"status":1,"create_time":"2018-07-09 17:16:31","update_time":"2018-07-09 17:16:31"},{"id":6,"pid":0,"name":"测试","sorts":2,"status":1,"create_time":"2018-07-10 08:33:10","update_time":"2018-07-10 08:33:10"}]
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
         * name : 医护类
         * sorts : 1
         * status : 1
         * create_time : 2018-07-09 17:15:45
         * update_time : 2018-07-09 17:15:45
         */

        private int id;
        private int pid;
        private String name;
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
    }
}
