package com.botian.yihu.database;

import org.litepal.crud.DataSupport;

public class ShareData extends DataSupport {
    private int chapterId;
    private int columnid;

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getColumnid() {
        return columnid;
    }

    public void setColumnid(int columnid) {
        this.columnid = columnid;
    }
}
