package com.botian.yihu.beans;

import java.util.List;

public class VideoClassZip {
    private List<VideoClass.DataBean> data;
    private List<Adlist.DataBean> data2;

    public List<VideoClass.DataBean> getData() {
        return data;
    }

    public void setData(List<VideoClass.DataBean> data) {
        this.data = data;
    }

    public List<Adlist.DataBean> getData2() {
        return data2;
    }

    public void setData2(List<Adlist.DataBean> data2) {
        this.data2 = data2;
    }
}
