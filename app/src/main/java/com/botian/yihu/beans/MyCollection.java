package com.botian.yihu.beans;

import java.util.List;

public class MyCollection {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":32,"mid":1,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-15 16:02:07","update_time":"2018-05-15 16:02:07","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}},{"id":33,"mid":2,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-15 16:02:40","update_time":"2018-05-15 16:02:40","cl":0,"section":{"id":2,"title":"护理程序的理论基础不包括","A":"系统论","B":"解决问题论","C":"压力适应论","D":"信息交流论","E":"人的基本需要层次论","correct":"C","analysis":"护理程序的理论基础来源于系统论、人的基本需要层次论、信息交流论、解决问题论等，不包括压力适应论。故答案选C。"}}]
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
         * id : 32
         * mid : 1
         * userid : 14
         * sorts : 0
         * status : 0
         * create_time : 2018-05-15 16:02:07
         * update_time : 2018-05-15 16:02:07
         * cl : 0
         * section : {"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}
         */

        private int id;
        private int mid;
        private int userid;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private int cl;
        private SectionBean section;

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

        public SectionBean getSection() {
            return section;
        }

        public void setSection(SectionBean section) {
            this.section = section;
        }

        public static class SectionBean {
            /**
             * id : 1
             * title : 关于护理程序的概念，描述正确的是
             * A : 一种护理工作的分工类型
             * B : 一种护理工作的简化形式
             * C : 一种系统的解决问题的方法
             * D : 一种护理操作的模式
             * E : 一种护理操作的模式
             * correct : C
             * analysis : 护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。
             */

            private int id;
            private String title;
            private String A;
            private String B;
            private String C;
            private String D;
            private String E;
            private String correct;
            private String analysis;
            private String litpic;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }
    }
}
