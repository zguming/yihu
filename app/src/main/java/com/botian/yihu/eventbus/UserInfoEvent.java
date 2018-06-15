package com.botian.yihu.eventbus;

import android.graphics.Bitmap;

public class UserInfoEvent {
    private String message;
    private Bitmap photo;
    public UserInfoEvent(String message,Bitmap photo) {
        this.message = message;
        this.photo = photo;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
