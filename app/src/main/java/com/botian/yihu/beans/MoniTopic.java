package com.botian.yihu.beans;

import java.util.List;

public class MoniTopic {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"typeid":1,"mid":1,"title":"测试名称","A":"1","B":"sd","C":"s","D":"s","E":"s","correct":"A","analysis":"s","flag":null,"jumplink":null,"litpic":null,"writer":null,"keywords":null,"description":null,"status":1,"create_time":"2018-06-06 11:18:27","update_time":"1970-01-01 08:00:00"},{"id":2,"typeid":2,"mid":2,"title":"s","A":"d","B":"f","C":"d","D":"d","E":"d","correct":"A","analysis":"d","flag":null,"jumplink":null,"litpic":null,"writer":null,"keywords":null,"description":null,"status":1,"create_time":"2018-06-06 11:18:27","update_time":"1970-01-01 08:00:00"}]
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
         * id : 1
         * typeid : 1
         * mid : 1
         * title : 测试名称
         * A : 1
         * B : sd
         * C : s
         * D : s
         * E : s
         * correct : A
         * analysis : s
         * flag : null
         * jumplink : null
         * litpic : null
         * writer : null
         * keywords : null
         * description : null
         * status : 1
         * create_time : 2018-06-06 11:18:27
         * update_time : 1970-01-01 08:00:00
         */

        private int id;
        private int typeid;
        private int mid;
        private String title;
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String correct;
        private String analysis;
        private Object flag;
        private Object jumplink;
        private String litpic;
        private Object writer;
        private Object keywords;
        private Object description;
        private int status;
        private String create_time;
        private String update_time;
        private String titlecl;

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getTitlecl() {
            return titlecl;
        }

        public void setTitlecl(String titlecl) {
            this.titlecl = titlecl;
        }

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

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
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

        public Object getFlag() {
            return flag;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public Object getJumplink() {
            return jumplink;
        }

        public void setJumplink(Object jumplink) {
            this.jumplink = jumplink;
        }


        public Object getWriter() {
            return writer;
        }

        public void setWriter(Object writer) {
            this.writer = writer;
        }

        public Object getKeywords() {
            return keywords;
        }

        public void setKeywords(Object keywords) {
            this.keywords = keywords;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
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
    }
}
