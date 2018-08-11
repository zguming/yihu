package com.botian.yihu.beans;

import java.util.List;

public class VodeoBuyList {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":88,"userid":14,"column_id":1,"video_id":1,"directory_id":"1,2","videodata_id":"29,32","create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00"},{"id":89,"userid":14,"column_id":1,"video_id":1,"directory_id":"1","videodata_id":"29,32","create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00"}]
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
         * id : 88
         * userid : 14
         * column_id : 1
         * video_id : 1
         * directory_id : 1,2
         * videodata_id : 29,32
         * create_time : 1970-01-01 08:00:00
         * update_time : 1970-01-01 08:00:00
         */

        private int id;
        private int userid;
        private int column_id;
        private int video_id;
        private String directory_id;
        private String videodata_id;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }

        public String getDirectory_id() {
            return directory_id;
        }

        public void setDirectory_id(String directory_id) {
            this.directory_id = directory_id;
        }

        public String getVideodata_id() {
            return videodata_id;
        }

        public void setVideodata_id(String videodata_id) {
            this.videodata_id = videodata_id;
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
