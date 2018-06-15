package com.botian.yihu.beans;

import java.util.List;

public class VideoClass {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":1,"typename":"护士执业精讲","mid":1,"litpic":"/uploads/image/20180517/02b8592b7fa027a6445705e6c76f1d8f.jpg","content":"","sorts":0,"status":1,"create_time":"2018-05-10 13:57:09","update_time":"2018-05-17 16:48:33","share":2,"price":"500","flag":"c,p","jumplink":"www.baidu.com","click":0,"pl":3}]
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
         * typename : 护士执业精讲
         * mid : 1
         * litpic : /uploads/image/20180517/02b8592b7fa027a6445705e6c76f1d8f.jpg
         * content :
         * sorts : 0
         * status : 1
         * create_time : 2018-05-10 13:57:09
         * update_time : 2018-05-17 16:48:33
         * share : 2
         * price : 500
         * flag : c,p
         * jumplink : www.baidu.com
         * click : 0
         * pl : 3
         */

        private int id;
        private String typename;
        private int mid;
        private String litpic;
        private String content;
        private int sorts;
        private int status;
        private String create_time;
        private String update_time;
        private int share;
        private String price;
        private String flag;
        private String jumplink;
        private int click;
        private int pl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getJumplink() {
            return jumplink;
        }

        public void setJumplink(String jumplink) {
            this.jumplink = jumplink;
        }

        public int getClick() {
            return click;
        }

        public void setClick(int click) {
            this.click = click;
        }

        public int getPl() {
            return pl;
        }

        public void setPl(int pl) {
            this.pl = pl;
        }
    }
}
