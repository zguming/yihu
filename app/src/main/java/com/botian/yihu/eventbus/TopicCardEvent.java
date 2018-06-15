package com.botian.yihu.eventbus;

public class TopicCardEvent {
    private int message;

    public TopicCardEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}


