package com.botian.yihu.beans;

import java.util.List;

public class NewsZip {
    private List<NewsLable.DataBean> data;
    private NewsList.DataBeanX data2;

    public List<NewsLable.DataBean> getData() {
        return data;
    }

    public void setData(List<NewsLable.DataBean> data) {
        this.data = data;
    }

    public NewsList.DataBeanX getData2() {
        return data2;
    }

    public void setData2(NewsList.DataBeanX data2) {
        this.data2 = data2;
    }
}
