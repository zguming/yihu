package com.botian.yihu.beans;

import java.util.List;

public class SendNewsComment {

    /**
     * code : 200
     * msg : 提交成功
     * invalidFilter : []
     * data : {"id":50,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-06-25 16:00:47","update_time":"2018-06-25 16:00:47","content":"大山方框囧","cai_num":0}
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
         * id : 50
         * mid : 29
         * userid : 14
         * sorts : 0
         * status : 0
         * create_time : 2018-06-25 16:00:47
         * update_time : 2018-06-25 16:00:47
         * content : 大山方框囧
         * cai_num : 0
         */

        private int id;
        private int mid;
        private int userid;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private String content;
        private int cai_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCai_num() {
            return cai_num;
        }

        public void setCai_num(int cai_num) {
            this.cai_num = cai_num;
        }
    }
}
