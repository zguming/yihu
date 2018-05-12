package com.botian.yihu.database;

import org.litepal.crud.DataSupport;

public class ChapterPracticeList extends DataSupport {
    /**
     * id : 1
     * pid : 0
     * typename : 第一章  基础护理知识和技能
     * mid : 1
     * litpic : null
     * content : null
     * sorts : 0
     * status : 1
     * create_time : 2018-05-08 10:45:38
     * update_time : 2018-05-08 14:04:45
     * zhenti : 2
     * share : 0
     */

    private int noid;
    private int pid;
    private String typename;
    private int mid;
    private Object litpic;
    private Object content;
    private int sorts;
    private int status;
    private String create_time;
    private String update_time;
    private int zhenti;
    private int share;

    public int getNoid() {
        return noid;
    }

    public void setNoid(int noid) {
        this.noid = noid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public Object getLitpic() {
        return litpic;
    }

    public void setLitpic(Object litpic) {
        this.litpic = litpic;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
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

    public int getZhenti() {
        return zhenti;
    }

    public void setZhenti(int zhenti) {
        this.zhenti = zhenti;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

}
