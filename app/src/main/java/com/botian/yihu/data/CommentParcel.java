package com.botian.yihu.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentParcel implements Parcelable {
    int id;
    String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
    }

    public CommentParcel() {
    }

    protected CommentParcel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<CommentParcel> CREATOR = new Parcelable.Creator<CommentParcel>() {
        @Override
        public CommentParcel createFromParcel(Parcel source) {
            return new CommentParcel(source);
        }

        @Override
        public CommentParcel[] newArray(int size) {
            return new CommentParcel[size];
        }
    };
}
