package com.botian.yihu.beans;

import java.util.List;

public class MyNoteSection {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : {"total":4,"per_page":20,"current_page":1,"last_page":1,"data":[{"id":34,"mid":1,"userid":2,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}},{"id":35,"mid":17,"userid":2,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cl":1,"section":{"id":17,"title":"护理诊断指出护理方向，有利于","A":"收集客观资料","B":"制定护理措施  ","C":"实施护理措施","D":"进行护理评估","E":"修改护理计划","correct":"B","analysis":"护理诊断在整个护理程序中是很重要的一个步骤，从分析资料、确认患者存在的问题以至得出护理诊断，是下一步制定护理计划的基础。故答案选B。"}},{"id":36,"mid":1,"userid":1,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}},{"id":37,"mid":1,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-17 09:33:01","update_time":"2018-05-17 09:33:01","content":"nihk","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}}]}
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
         * total : 4
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":34,"mid":1,"userid":2,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}},{"id":35,"mid":17,"userid":2,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试啊","cl":1,"section":{"id":17,"title":"护理诊断指出护理方向，有利于","A":"收集客观资料","B":"制定护理措施  ","C":"实施护理措施","D":"进行护理评估","E":"修改护理计划","correct":"B","analysis":"护理诊断在整个护理程序中是很重要的一个步骤，从分析资料、确认患者存在的问题以至得出护理诊断，是下一步制定护理计划的基础。故答案选B。"}},{"id":36,"mid":1,"userid":1,"sorts":0,"status":0,"create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","content":"测试","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}},{"id":37,"mid":1,"userid":14,"sorts":0,"status":0,"create_time":"2018-05-17 09:33:01","update_time":"2018-05-17 09:33:01","content":"nihk","cl":0,"section":{"id":1,"title":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"C","analysis":"护理程序是护士对护理对象进行护理时所应用的工作程序，是一种系统解决问题的方法，是一个持续、循环、动态变化的过程。故答案选C。"}}]
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
             * id : 34
             * mid : 1
             * userid : 2
             * sorts : 0
             * status : 0
             * create_time : 1970-01-01 08:00:00
             * update_time : 1970-01-01 08:00:00
             * content : 测试啊
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
            private String content;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
                private String material;
                private String note;

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

                public String getMaterial() {
                    return material;
                }

                public void setMaterial(String material) {
                    this.material = material;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }
            }
        }
    }
}
