package com.botian.yihu.beans;

import java.util.List;

public class Adlist {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":4,"mid":1,"webname":"首页","url":"","sorts":0,"status":1,"create_time":"2018-05-23 09:37:33","update_time":"2018-05-23 14:44:01","litpic":"/uploads/image/20180523/315d469cfd7df302f2debae80bb2f62f.jpg"},{"id":5,"mid":1,"webname":"首页","url":"","sorts":0,"status":1,"create_time":"2018-05-23 14:43:46","update_time":"2018-05-23 14:43:46","litpic":"/uploads/image/20180523/b23872cf15126441a936e414b2d76f3f.jpg"},{"id":6,"mid":1,"webname":"首页","url":"","sorts":0,"status":1,"create_time":"2018-05-23 14:44:27","update_time":"2018-05-23 14:44:27","litpic":"/uploads/image/20180523/4294ec65974ea5e3215ba006cf374476.jpg"},{"id":7,"mid":1,"webname":"首页","url":"","sorts":0,"status":1,"create_time":"2018-05-23 14:44:51","update_time":"2018-05-23 14:44:51","litpic":"/uploads/image/20180523/0e8fccd64b0ed4cbb82a4d05df8d261f.jpg"}]
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
         * id : 4
         * mid : 1
         * webname : 首页
         * url :
         * sorts : 0
         * status : 1
         * create_time : 2018-05-23 09:37:33
         * update_time : 2018-05-23 14:44:01
         * litpic : /uploads/image/20180523/315d469cfd7df302f2debae80bb2f62f.jpg
         */

        private int id;
        private int mid;
        private String webname;
        private String url;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private String litpic;

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

        public String getWebname() {
            return webname;
        }

        public void setWebname(String webname) {
            this.webname = webname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
    }
}
