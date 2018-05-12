package com.botian.yihu.data;
import java.util.List;

public class PracticeListBean {
    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":40,"co_id":2,"title":"护士执业资格考试","sort":0,"open":1,"addtime":1523863736,"uptime":null,"pid":0,"coname":"护士执业","child":[{"id":35,"co_id":2,"title":"第一章 基础护理知识和技能","sort":0,"open":1,"addtime":1523862199,"uptime":1523863766,"pid":40,"coname":"护士执业","child":[{"id":38,"co_id":2,"title":"第二节 护士执业防护","sort":0,"open":1,"addtime":1523862857,"uptime":1523863782,"pid":35,"coname":"护士执业"},{"id":37,"co_id":2,"title":"第一节 护理程序","sort":0,"open":1,"addtime":1523862502,"uptime":1523863775,"pid":35,"coname":"护士执业"},{"id":39,"co_id":2,"title":"第三节 医院和住院环境","sort":0,"open":1,"addtime":1523862875,"uptime":1523863787,"pid":35,"coname":"护士执业"}]}]}]
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
         * id : 40
         * co_id : 2
         * title : 护士执业资格考试
         * sort : 0
         * open : 1
         * addtime : 1523863736
         * uptime : null
         * pid : 0
         * coname : 护士执业
         * child : [{"id":35,"co_id":2,"title":"第一章 基础护理知识和技能","sort":0,"open":1,"addtime":1523862199,"uptime":1523863766,"pid":40,"coname":"护士执业","child":[{"id":38,"co_id":2,"title":"第二节 护士执业防护","sort":0,"open":1,"addtime":1523862857,"uptime":1523863782,"pid":35,"coname":"护士执业"},{"id":37,"co_id":2,"title":"第一节 护理程序","sort":0,"open":1,"addtime":1523862502,"uptime":1523863775,"pid":35,"coname":"护士执业"},{"id":39,"co_id":2,"title":"第三节 医院和住院环境","sort":0,"open":1,"addtime":1523862875,"uptime":1523863787,"pid":35,"coname":"护士执业"}]}]
         */

        private int id;
        private int co_id;
        private String title;
        private int sort;
        private int open;
        private int addtime;
        private Object uptime;
        private int pid;
        private String coname;
        private List<ChildBeanX> child;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCo_id() {
            return co_id;
        }

        public void setCo_id(int co_id) {
            this.co_id = co_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public Object getUptime() {
            return uptime;
        }

        public void setUptime(Object uptime) {
            this.uptime = uptime;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }

        public List<ChildBeanX> getChild() {
            return child;
        }

        public void setChild(List<ChildBeanX> child) {
            this.child = child;
        }

        public static class ChildBeanX {
            /**
             * id : 35
             * co_id : 2
             * title : 第一章 基础护理知识和技能
             * sort : 0
             * open : 1
             * addtime : 1523862199
             * uptime : 1523863766
             * pid : 40
             * coname : 护士执业
             * child : [{"id":38,"co_id":2,"title":"第二节 护士执业防护","sort":0,"open":1,"addtime":1523862857,"uptime":1523863782,"pid":35,"coname":"护士执业"},{"id":37,"co_id":2,"title":"第一节 护理程序","sort":0,"open":1,"addtime":1523862502,"uptime":1523863775,"pid":35,"coname":"护士执业"},{"id":39,"co_id":2,"title":"第三节 医院和住院环境","sort":0,"open":1,"addtime":1523862875,"uptime":1523863787,"pid":35,"coname":"护士执业"}]
             */

            private int id;
            private int co_id;
            private String title;
            private int sort;
            private int open;
            private int addtime;
            private int uptime;
            private int pid;
            private String coname;
            private List<ChildBean> child;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCo_id() {
                return co_id;
            }

            public void setCo_id(int co_id) {
                this.co_id = co_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getOpen() {
                return open;
            }

            public void setOpen(int open) {
                this.open = open;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getUptime() {
                return uptime;
            }

            public void setUptime(int uptime) {
                this.uptime = uptime;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getConame() {
                return coname;
            }

            public void setConame(String coname) {
                this.coname = coname;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * id : 38
                 * co_id : 2
                 * title : 第二节 护士执业防护
                 * sort : 0
                 * open : 1
                 * addtime : 1523862857
                 * uptime : 1523863782
                 * pid : 35
                 * coname : 护士执业
                 */

                private int id;
                private int co_id;
                private String title;
                private int sort;
                private int open;
                private int addtime;
                private int uptime;
                private int pid;
                private String coname;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getCo_id() {
                    return co_id;
                }

                public void setCo_id(int co_id) {
                    this.co_id = co_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public int getOpen() {
                    return open;
                }

                public void setOpen(int open) {
                    this.open = open;
                }

                public int getAddtime() {
                    return addtime;
                }

                public void setAddtime(int addtime) {
                    this.addtime = addtime;
                }

                public int getUptime() {
                    return uptime;
                }

                public void setUptime(int uptime) {
                    this.uptime = uptime;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getConame() {
                    return coname;
                }

                public void setConame(String coname) {
                    this.coname = coname;
                }
            }
        }
    }
}
