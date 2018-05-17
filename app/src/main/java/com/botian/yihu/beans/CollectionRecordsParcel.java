package com.botian.yihu.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CollectionRecordsParcel implements Parcelable {


    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":7,"user_id":2646,"topic_id":5110,"zj":"","zhenti":"","sort":0,"open":0,"addtime":1525404962,"uptime":0,"name":"护士章节","A":"护士章节","B":"护士章节","C":"护士章节","D":"护士章节","E":"护士章节","correct":"A","analysis":"护士章节","image":""}]
     */
    private int position;
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 7
         * user_id : 2646
         * topic_id : 5110
         * addtime : 1525404962
         * uptime : 0
         * name : 护士章节
         * A : 护士章节
         * B : 护士章节
         * C : 护士章节
         * D : 护士章节
         * E : 护士章节
         * correct : A
         * analysis : 护士章节
         * image :
         */

        private int id;
        private int user_id;
        private int topic_id;
        private int addtime;
        private int uptime;
        private String name;
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String correct;
        private String analysis;
        private String image;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getUptime() {
            return uptime;
        }

        public void setUptime(int uptime) {
            this.uptime = uptime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getA() {
            return A;
        }

        public void setA(String A) {
            this.A = A;
        }

        public String getB() {
            return B;
        }

        public void setB(String B) {
            this.B = B;
        }

        public String getC() {
            return C;
        }

        public void setC(String C) {
            this.C = C;
        }

        public String getD() {
            return D;
        }

        public void setD(String D) {
            this.D = D;
        }

        public String getE() {
            return E;
        }

        public void setE(String E) {
            this.E = E;
        }

        public String getCorrect() {
            return correct;
        }

        public void setCorrect(String correct) {
            this.correct = correct;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.user_id);
            dest.writeInt(this.topic_id);
            dest.writeInt(this.addtime);
            dest.writeInt(this.uptime);
            dest.writeString(this.name);
            dest.writeString(this.A);
            dest.writeString(this.B);
            dest.writeString(this.C);
            dest.writeString(this.D);
            dest.writeString(this.E);
            dest.writeString(this.correct);
            dest.writeString(this.analysis);
            dest.writeString(this.image);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.user_id = in.readInt();
            this.topic_id = in.readInt();
            this.addtime = in.readInt();
            this.uptime = in.readInt();
            this.name = in.readString();
            this.A = in.readString();
            this.B = in.readString();
            this.C = in.readString();
            this.D = in.readString();
            this.E = in.readString();
            this.correct = in.readString();
            this.analysis = in.readString();
            this.image = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
        dest.writeTypedList(this.data);
    }

    public CollectionRecordsParcel() {
    }

    protected CollectionRecordsParcel(Parcel in) {
        this.position = in.readInt();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<CollectionRecordsParcel> CREATOR = new Parcelable.Creator<CollectionRecordsParcel>() {
        @Override
        public CollectionRecordsParcel createFromParcel(Parcel source) {
            return new CollectionRecordsParcel(source);
        }

        @Override
        public CollectionRecordsParcel[] newArray(int size) {
            return new CollectionRecordsParcel[size];
        }
    };
}
