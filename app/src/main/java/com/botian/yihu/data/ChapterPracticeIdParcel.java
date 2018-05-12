package com.botian.yihu.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ChapterPracticeIdParcel implements Parcelable {
    String co_id;
    String id;

    public String getCo_id() {
        return co_id;
    }

    public void setCo_id(String co_id) {
        this.co_id = co_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.co_id);
        dest.writeString(this.id);
    }

    public ChapterPracticeIdParcel() {
    }

    protected ChapterPracticeIdParcel(Parcel in) {
        this.co_id = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<ChapterPracticeIdParcel> CREATOR = new Parcelable.Creator<ChapterPracticeIdParcel>() {
        @Override
        public ChapterPracticeIdParcel createFromParcel(Parcel source) {
            return new ChapterPracticeIdParcel(source);
        }

        @Override
        public ChapterPracticeIdParcel[] newArray(int size) {
            return new ChapterPracticeIdParcel[size];
        }
    };
}
