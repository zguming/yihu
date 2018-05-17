package com.botian.yihu.beans;

import java.util.List;

public class PracticeBean {


    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":5110,"name":"护士章节","A":"护士章节","B":"护士章节","C":"护士章节","D":"护士章节","E":"护士章节","correct":"A","analysis":"护士章节","image":""}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5110
         * name : 护士章节
         * A : 护士章节
         * B : 护士章节
         * C : 护士章节
         * D : 护士章节
         * E : 护士章节
         * correct : A
         * analysis : 护士章节
         * image :
         */

        private int id;
        private String name;
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String correct;
        private String analysis;
        private String image;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
