package com.botian.yihu.eventbus;

public class LoginEvent {
    private int message;

    public LoginEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}


