package com.botian.yihu.eventbus;

public class VideoEvent {
    private String cover;
    private String url;
    private String title;
    public VideoEvent(String cover,String url,String title) {
        this.cover = cover;
        this.url = url;
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
