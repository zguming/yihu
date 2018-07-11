package com.botian.yihu.eventbus;

public class TopicCardEvent1 {
    private int message;

    public TopicCardEvent1(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}


