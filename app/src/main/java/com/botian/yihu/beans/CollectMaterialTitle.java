package com.botian.yihu.beans;

import java.util.List;

public class CollectMaterialTitle {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : {"total":1,"per_page":20,"current_page":1,"last_page":1,"data":[{"id":51,"mid":17,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:23","update_time":"2018-07-03 11:01:23","cl":1,"material":{"id":17,"typeid":22,"title":"测试材料分析题","litpic":"","status":1,"zhenti":2,"create_time":"2018-05-08 15:07:17","update_time":"2018-05-23 12:54:48","sc":4,"cl":1,"bj":2,"pl":2,"dz":1}}]}
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
         * total : 1
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":51,"mid":17,"userid":14,"sorts":0,"status":0,"create_time":"2018-07-03 11:01:23","update_time":"2018-07-03 11:01:23","cl":1,"material":{"id":17,"typeid":22,"title":"测试材料分析题","litpic":"","status":1,"zhenti":2,"create_time":"2018-05-08 15:07:17","update_time":"2018-05-23 12:54:48","sc":4,"cl":1,"bj":2,"pl":2,"dz":1}}]
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
             * id : 51
             * mid : 17
             * userid : 14
             * sorts : 0
             * status : 0
             * create_time : 2018-07-03 11:01:23
             * update_time : 2018-07-03 11:01:23
             * cl : 1
             * material : {"id":17,"typeid":22,"title":"测试材料分析题","litpic":"","status":1,"zhenti":2,"create_time":"2018-05-08 15:07:17","update_time":"2018-05-23 12:54:48","sc":4,"cl":1,"bj":2,"pl":2,"dz":1}
             */

            private int id;
            private int mid;
            private int userid;
            private int sorts;
            private int status;
            private String create_time;
            private String update_time;
            private int cl;
            private MaterialBean material;

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

            public MaterialBean getMaterial() {
                return material;
            }

            public void setMaterial(MaterialBean material) {
                this.material = material;
            }

            public static class MaterialBean {
                /**
                 * id : 17
                 * typeid : 22
                 * title : 测试材料分析题
                 * litpic :
                 * status : 1
                 * zhenti : 2
                 * create_time : 2018-05-08 15:07:17
                 * update_time : 2018-05-23 12:54:48
                 * sc : 4
                 * cl : 1
                 * bj : 2
                 * pl : 2
                 * dz : 1
                 */

                private int id;
                private int typeid;
                private String title;
                private String litpic;
                private int status;
                private int zhenti;
                private String create_time;
                private String update_time;
                private int sc;
                private int cl;
                private int bj;
                private int pl;
                private int dz;

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

                public int getZhenti() {
                    return zhenti;
                }

                public void setZhenti(int zhenti) {
                    this.zhenti = zhenti;
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

                public int getSc() {
                    return sc;
                }

                public void setSc(int sc) {
                    this.sc = sc;
                }

                public int getCl() {
                    return cl;
                }

                public void setCl(int cl) {
                    this.cl = cl;
                }

                public int getBj() {
                    return bj;
                }

                public void setBj(int bj) {
                    this.bj = bj;
                }

                public int getPl() {
                    return pl;
                }

                public void setPl(int pl) {
                    this.pl = pl;
                }

                public int getDz() {
                    return dz;
                }

                public void setDz(int dz) {
                    this.dz = dz;
                }
            }
        }
    }
}
