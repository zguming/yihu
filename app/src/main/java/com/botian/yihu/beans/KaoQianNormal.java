package com.botian.yihu.beans;

import java.util.List;

public class KaoQianNormal {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":33,"bet_id":29,"title":"测试","A":"1","B":"2","C":"3","D":"4","E":"5","correct":"A","analysis":"1","litpic":"","status":1,"create_time":"1970-01-01 08:00:00","update_time":"2018-08-09 14:50:29","mids":2}]
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
         * id : 33
         * bet_id : 29
         * title : 测试
         * A : 1
         * B : 2
         * C : 3
         * D : 4
         * E : 5
         * correct : A
         * analysis : 1
         * litpic :
         * status : 1
         * create_time : 1970-01-01 08:00:00
         * update_time : 2018-08-09 14:50:29
         * mids : 2
         */

        private int id;
        private int bet_id;
        private String title;
        private String Titlecl;
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String correct;
        private String analysis;
        private String litpic;
        private int status;
        private String create_time;
        private String update_time;
        private int mids;
        private int typeid;
        private int cl;

        public String getTitlecl() {
            return Titlecl;
        }

        public void setTitlecl(String titlecl) {
            Titlecl = titlecl;
        }

        public int getCl() {
            return cl;
        }

        public void setCl(int cl) {
            this.cl = cl;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBet_id() {
            return bet_id;
        }

        public void setBet_id(int bet_id) {
            this.bet_id = bet_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getA() {
            return A;
        }

        public void setA(String A) {
            this.A = A;
        }

        public String getB() {
            return B;
        }

        public void setB(String B) {
            this.B = B;
        }

        public String getC() {
            return C;
        }

        public void setC(String C) {
            this.C = C;
        }

        public String getD() {
            return D;
        }

        public void setD(String D) {
            this.D = D;
        }

        public String getE() {
            return E;
        }

        public void setE(String E) {
            this.E = E;
        }

        public String getCorrect() {
            return correct;
        }

        public void setCorrect(String correct) {
            this.correct = correct;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
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

        public int getMids() {
            return mids;
        }

        public void setMids(int mids) {
            this.mids = mids;
        }
    }
}
