package com.botian.yihu.data;

import java.util.List;

public class VideoListBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":54,"co_id":2,"vi_id":10,"title":"第一篇","content":"","buy":1,"sort":0,"open":1,"addtime":1523934597,"uptime":1523934903,"pid":0,"columnname":"护士执业","videoname":"护士执业精英班","child":[{"id":55,"co_id":2,"vi_id":10,"title":"第一节","content":"","buy":1,"sort":0,"open":1,"addtime":1523934606,"uptime":1523934908,"pid":54,"columnname":"护士执业","videoname":"护士执业精英班"},{"id":56,"co_id":2,"vi_id":10,"title":"第二节","content":"","buy":1,"sort":0,"open":1,"addtime":1523934617,"uptime":1523934914,"pid":54,"columnname":"护士执业","videoname":"护士执业精英班"}]}]
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
         * id : 54
         * co_id : 2
         * vi_id : 10
         * title : 第一篇
         * content :
         * buy : 1
         * sort : 0
         * open : 1
         * addtime : 1523934597
         * uptime : 1523934903
         * pid : 0
         * columnname : 护士执业
         * videoname : 护士执业精英班
         * child : [{"id":55,"co_id":2,"vi_id":10,"title":"第一节","content":"","buy":1,"sort":0,"open":1,"addtime":1523934606,"uptime":1523934908,"pid":54,"columnname":"护士执业","videoname":"护士执业精英班"},{"id":56,"co_id":2,"vi_id":10,"title":"第二节","content":"","buy":1,"sort":0,"open":1,"addtime":1523934617,"uptime":1523934914,"pid":54,"columnname":"护士执业","videoname":"护士执业精英班"}]
         */

        private int id;
        private int co_id;
        private int vi_id;
        private String title;
        private String content;
        private int buy;
        private int sort;
        private int open;
        private int addtime;
        private int uptime;
        private int pid;
        private String columnname;
        private String videoname;
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

        public int getVi_id() {
            return vi_id;
        }

        public void setVi_id(int vi_id) {
            this.vi_id = vi_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
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

        public String getColumnname() {
            return columnname;
        }

        public void setColumnname(String columnname) {
            this.columnname = columnname;
        }

        public String getVideoname() {
            return videoname;
        }

        public void setVideoname(String videoname) {
            this.videoname = videoname;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 55
             * co_id : 2
             * vi_id : 10
             * title : 第一节
             * content :
             * buy : 1
             * sort : 0
             * open : 1
             * addtime : 1523934606
             * uptime : 1523934908
             * pid : 54
             * columnname : 护士执业
             * videoname : 护士执业精英班
             */

            private int id;
            private int co_id;
            private int vi_id;
            private String title;
            private String content;
            private int buy;
            private int sort;
            private int open;
            private int addtime;
            private int uptime;
            private int pid;
            private String columnname;
            private String videoname;

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

            public int getVi_id() {
                return vi_id;
            }

            public void setVi_id(int vi_id) {
                this.vi_id = vi_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getBuy() {
                return buy;
            }

            public void setBuy(int buy) {
                this.buy = buy;
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

            public String getColumnname() {
                return columnname;
            }

            public void setColumnname(String columnname) {
                this.columnname = columnname;
            }

            public String getVideoname() {
                return videoname;
            }

            public void setVideoname(String videoname) {
                this.videoname = videoname;
            }
        }
    }
}
