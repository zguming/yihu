package com.botian.yihu.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentParcel implements Parcelable {
    int id;
    String title;
    String cl;

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

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.cl);
    }

    public CommentParcel() {
    }

    protected CommentParcel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.cl = in.readString();
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
