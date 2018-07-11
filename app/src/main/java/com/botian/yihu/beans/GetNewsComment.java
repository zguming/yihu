package com.botian.yihu.beans;

import java.util.List;

public class GetNewsComment {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : {"total":10,"per_page":20,"current_page":1,"last_page":1,"data":[{"id":41,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-31 11:10:06","update_time":"2018-05-31 11:10:06","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":42,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-31 11:10:40","update_time":"2018-05-31 11:10:40","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":43,"mid":29,"userid":14,"sorts":0,"status":1,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":44,"mid":29,"userid":14,"sorts":0,"status":1,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":45,"mid":12,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:11:10","update_time":"2018-06-02 16:11:10","content":"222","cai_num":0,"usres":null},{"id":46,"mid":12,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:12:49","update_time":"2018-06-02 16:12:49","content":"222","cai_num":0,"usres":null},{"id":47,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:13:37","update_time":"2018-06-02 16:13:37","content":"222","cai_num":0,"usres":null},{"id":48,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:13:54","update_time":"2018-06-02 16:13:54","content":"222","cai_num":0,"usres":null},{"id":49,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:23:03","update_time":"2018-06-02 16:23:03","content":"222","cai_num":0,"usres":null},{"id":50,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-06-25 16:00:47","update_time":"2018-06-25 16:00:47","content":"大山方框囧","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}}]}
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
         * total : 10
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":41,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-31 11:10:06","update_time":"2018-05-31 11:10:06","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":42,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-31 11:10:40","update_time":"2018-05-31 11:10:40","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":43,"mid":29,"userid":14,"sorts":0,"status":1,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":44,"mid":29,"userid":14,"sorts":0,"status":1,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}},{"id":45,"mid":12,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:11:10","update_time":"2018-06-02 16:11:10","content":"222","cai_num":0,"usres":null},{"id":46,"mid":12,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:12:49","update_time":"2018-06-02 16:12:49","content":"222","cai_num":0,"usres":null},{"id":47,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:13:37","update_time":"2018-06-02 16:13:37","content":"222","cai_num":0,"usres":null},{"id":48,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:13:54","update_time":"2018-06-02 16:13:54","content":"222","cai_num":0,"usres":null},{"id":49,"mid":29,"userid":11,"sorts":0,"status":0,"create_time":"2018-06-02 16:23:03","update_time":"2018-06-02 16:23:03","content":"222","cai_num":0,"usres":null},{"id":50,"mid":29,"userid":14,"sorts":0,"status":0,"create_time":"2018-06-25 16:00:47","update_time":"2018-06-25 16:00:47","content":"大山方框囧","cai_num":0,"usres":{"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}}]
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
             * id : 41
             * mid : 29
             * userid : 14
             * sorts : 0
             * status : 0
             * create_time : 2018-05-31 11:10:06
             * update_time : 2018-05-31 11:10:06
             * content : 测试啊
             * cai_num : 0
             * usres : {"id":14,"username":"聊","password":"e10adc3949ba59abbe56e057f20f883e","moblie":"15113993657","logins":19,"create_time":"2018-05-11 18:13:10","update_time":"1970-01-01 08:00:00","reg_ip":"123.149.7.123","last_time":1529913416,"last_ip":"","status":0,"version":"1.0.0","token":"933438bacd7c38a684be300efa6704fc","sex":0,"avatar":"/uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg"}
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
            private UsresBean usres;

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

            public UsresBean getUsres() {
                return usres;
            }

            public void setUsres(UsresBean usres) {
                this.usres = usres;
            }

            public static class UsresBean {
                /**
                 * id : 14
                 * username : 聊
                 * password : e10adc3949ba59abbe56e057f20f883e
                 * moblie : 15113993657
                 * logins : 19
                 * create_time : 2018-05-11 18:13:10
                 * update_time : 1970-01-01 08:00:00
                 * reg_ip : 123.149.7.123
                 * last_time : 1529913416
                 * last_ip :
                 * status : 0
                 * version : 1.0.0
                 * token : 933438bacd7c38a684be300efa6704fc
                 * sex : 0
                 * avatar : /uploads/users/20180601/e6b32f9bb6f693b553fdbdbf3f24c2d2.jpg
                 */

                private int id;
                private String username;
                private String password;
                private String moblie;
                private int logins;
                private String create_time;
                private String update_time;
                private String reg_ip;
                private int last_time;
                private String last_ip;
                private int status;
                private String version;
                private String token;
                private int sex;
                private String avatar;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getMoblie() {
                    return moblie;
                }

                public void setMoblie(String moblie) {
                    this.moblie = moblie;
                }

                public int getLogins() {
                    return logins;
                }

                public void setLogins(int logins) {
                    this.logins = logins;
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

                public String getReg_ip() {
                    return reg_ip;
                }

                public void setReg_ip(String reg_ip) {
                    this.reg_ip = reg_ip;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getLast_ip() {
                    return last_ip;
                }

                public void setLast_ip(String last_ip) {
                    this.last_ip = last_ip;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }
            }
        }
    }
}
