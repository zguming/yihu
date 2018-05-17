package com.botian.yihu.beans;

import java.util.List;

public class SubjectBean {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"name":"护士执业","sorts":1,"status":1,"create_time":"2018-05-04 08:50:08","update_time":"2018-05-07 15:54:00"},{"id":2,"name":"护师执业","sorts":2,"status":1,"create_time":"2018-05-04 08:50:49","update_time":"2018-05-04 08:50:49"},{"id":3,"name":"主管护师","sorts":3,"status":1,"create_time":"2018-05-04 08:50:57","update_time":"2018-05-04 08:50:57"}]
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
         * name : 护士执业
         * sorts : 1
         * status : 1
         * create_time : 2018-05-04 08:50:08
         * update_time : 2018-05-07 15:54:00
         */

        private int id;
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
