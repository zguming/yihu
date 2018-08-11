package com.botian.yihu.beans;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class LoginBean {


    /**
     * code : 200
     * msg : 登陆成功
     * invalidFilter : []
     * data : {"id":12,"token":"1d0f7d2e68f24ad9ccb19106461f91dc","username":"我？♥✔","moblie":"15113993657"}
     */

    private int code;
    private String msg;
    private DataBean data;
    //private List<?> invalidFilter;

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

    //public List<?> getInvalidFilter() {
        //return invalidFilter;
    //}

    //public void setInvalidFilter(List<?> invalidFilter) {
        //this.invalidFilter = invalidFilter;
    //}

    public static class DataBean {
        /**
         * id : 12
         * token : 1d0f7d2e68f24ad9ccb19106461f91dc
         * username : 我？♥✔
         * moblie : 15113993657
         */

        private int id;
        //private String token;
        private String username;
        private String moblie;
        private String avatar;
        private int sex;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        //public String getToken() {
            //return token;
        //}

        //public void setToken(String token) {
            //this.token = token;
        //}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMoblie() {
            return moblie;
        }

        public void setMoblie(String moblie) {
            this.moblie = moblie;
        }
    }
}
