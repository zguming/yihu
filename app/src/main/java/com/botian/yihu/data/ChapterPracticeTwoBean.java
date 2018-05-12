package com.botian.yihu.data;

import java.util.List;

public class ChapterPracticeTwoBean {


    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":44,"co_id":1,"title":"第一节 护理程序","sort":0,"open":0,"addtime":1524532313,"uptime":1524625208,"pid":43,"rules":null,"share":0,"zhenti":2,"coname":"护士执业"},{"id":50,"co_id":1,"title":"第二节 护士执业防护","sort":0,"open":0,"addtime":1524539095,"uptime":1524625198,"pid":43,"rules":null,"share":0,"zhenti":2,"coname":"护士执业"}]
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
         * id : 44
         * co_id : 1
         * title : 第一节 护理程序
         * sort : 0
         * open : 0
         * addtime : 1524532313
         * uptime : 1524625208
         * pid : 43
         * rules : null
         * share : 0
         * zhenti : 2
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
        private Object rules;
        private int share;
        private int zhenti;
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

        public Object getRules() {
            return rules;
        }

        public void setRules(Object rules) {
            this.rules = rules;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getZhenti() {
            return zhenti;
        }

        public void setZhenti(int zhenti) {
            this.zhenti = zhenti;
        }

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }
    }
}
