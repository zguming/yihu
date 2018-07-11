package com.botian.yihu.beans;

import java.util.List;

public class MyCollection {


    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : {"total":2,"per_page":20,"current_page":1,"last_page":1,"data":[{"id":50,"mid":1,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:13","update_time":"2018-07-03 11:01:13","cl":0,"material":null},{"id":52,"mid":23,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:33","update_time":"2018-07-03 11:01:33","cl":0,"material":null}]}
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
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":50,"mid":1,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:13","update_time":"2018-07-03 11:01:13","cl":0,"material":null},{"id":52,"mid":23,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:33","update_time":"2018-07-03 11:01:33","cl":0,"material":null}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
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
             * id : 50
             * mid : 1
             * userid : 14
             * sorts : 0
             * status : 0
             * create_time : 2018-07-03 11:01:13
             * update_time : 2018-07-03 11:01:13
             * cl : 0
             * material : null
             */

            private int id;
            private int mid;
            private int userid;
            private int sorts;
            private int status;
            private String create_time;
            private String update_time;
            private int cl;
            private Object material;

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

            public int getCl() {
                return cl;
            }

            public void setCl(int cl) {
                this.cl = cl;
            }

            public Object getMaterial() {
                return material;
            }

            public void setMaterial(Object material) {
                this.material = material;
            }
        }
    }
}
