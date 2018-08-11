package com.botian.yihu.beans;

import java.util.List;

public class Live {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":2,"column_id":2,"teacher_id":2,"title":"直播","money":"0.00","date":"2018-08-02","time_start":"8：00","time_end":"23：00","useid":"15343_fb12bff249","code":"http://15343.liveplay.myqcloud.com/live/15343_fb12bff249.flv","accesskey":"1254402451","secretkey":"@TGS#aTEMP56EV","create_time":"1970-01-01 08:00:00","update_time":"2018-08-02 17:17:50","status":1,"sorts":1,"buy":1,"Zhteacher":{"id":2,"name":"黑老师","img":"/uploads/teacher_image/20180802/4c9a44efe41e8e9e9ec6da0aa381a7e3.jpg"}}]
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
         * id : 2
         * column_id : 2
         * teacher_id : 2
         * title : 直播
         * money : 0.00
         * date : 2018-08-02
         * time_start : 8：00
         * time_end : 23：00
         * useid : 15343_fb12bff249
         * code : http://15343.liveplay.myqcloud.com/live/15343_fb12bff249.flv
         * accesskey : 1254402451
         * secretkey : @TGS#aTEMP56EV
         * create_time : 1970-01-01 08:00:00
         * update_time : 2018-08-02 17:17:50
         * status : 1
         * sorts : 1
         * buy : 1
         * Zhteacher : {"id":2,"name":"黑老师","img":"/uploads/teacher_image/20180802/4c9a44efe41e8e9e9ec6da0aa381a7e3.jpg"}
         */

        private int id;
        private int column_id;
        private int teacher_id;
        private String title;
        private String money;
        private String date;
        private String time_start;
        private String time_end;
        private String useid;
        private String code;
        private String accesskey;
        private String secretkey;
        private String create_time;
        private String update_time;
        private int status;
        private int sorts;
        private int buy;
        private ZhteacherBean Zhteacher;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public int getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(int teacher_id) {
            this.teacher_id = teacher_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getUseid() {
            return useid;
        }

        public void setUseid(String useid) {
            this.useid = useid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAccesskey() {
            return accesskey;
        }

        public void setAccesskey(String accesskey) {
            this.accesskey = accesskey;
        }

        public String getSecretkey() {
            return secretkey;
        }

        public void setSecretkey(String secretkey) {
            this.secretkey = secretkey;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public ZhteacherBean getZhteacher() {
            return Zhteacher;
        }

        public void setZhteacher(ZhteacherBean Zhteacher) {
            this.Zhteacher = Zhteacher;
        }

        public static class ZhteacherBean {
            /**
             * id : 2
             * name : 黑老师
             * img : /uploads/teacher_image/20180802/4c9a44efe41e8e9e9ec6da0aa381a7e3.jpg
             */

            private int id;
            private String name;
            private String img;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
