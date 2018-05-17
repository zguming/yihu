package com.botian.yihu.beans;

import java.util.List;

public class Material {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":17,"typeid":22,"title":"测试材料分析题","litpic":"","status":1,"create_time":"2018-05-08 15:07:17","update_time":"2018-05-08 15:07:28"}]
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
         * id : 17
         * typeid : 22
         * title : 测试材料分析题
         * litpic :
         * status : 1
         * create_time : 2018-05-08 15:07:17
         * update_time : 2018-05-08 15:07:28
         */

        private int id;
        private int typeid;
        private String title;
        private String litpic;
        private int status;
        private int cl;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
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

        public int getCl() {
            return cl;
        }

        public void setCl(int cl) {
            this.cl = cl;
        }
    }
}
