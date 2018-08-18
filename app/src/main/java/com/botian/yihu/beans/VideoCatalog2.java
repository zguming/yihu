package com.botian.yihu.beans;

import java.util.List;

public class VideoCatalog2 {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : {"total":2,"per_page":"1","current_page":"1","last_page":2,"data":[{"id":76,"pid":75,"typename":"专业实务","video_id":8,"content":"","sorts":0,"status":1,"create_time":"2018-08-15 09:44:17","update_time":"2018-08-15 09:57:21","buy":0,"mid":1,"mids":2}]}
     */

    private int code;
    private String msg;
    private DataBeanX data;
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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public List<?> getInvalidFilter() {
        return invalidFilter;
    }

    public void setInvalidFilter(List<?> invalidFilter) {
        this.invalidFilter = invalidFilter;
    }

    public static class DataBeanX {
        /**
         * total : 2
         * per_page : 1
         * current_page : 1
         * last_page : 2
         * data : [{"id":76,"pid":75,"typename":"专业实务","video_id":8,"content":"","sorts":0,"status":1,"create_time":"2018-08-15 09:44:17","update_time":"2018-08-15 09:57:21","buy":0,"mid":1,"mids":2}]
         */

        private int total;
        private String per_page;
        private String current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 76
             * pid : 75
             * typename : 专业实务
             * video_id : 8
             * content :
             * sorts : 0
             * status : 1
             * create_time : 2018-08-15 09:44:17
             * update_time : 2018-08-15 09:57:21
             * buy : 0
             * mid : 1
             * mids : 2
             */

            private int id;
            private int pid;
            private String typename;
            private int video_id;
            private String content;
            private int sorts;
            private int status;
            private String create_time;
            private String update_time;
            private int buy;
            private int mid;
            private int mids;

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

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getBuy() {
                return buy;
            }

            public void setBuy(int buy) {
                this.buy = buy;
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
        }
    }
}
