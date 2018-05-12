package com.botian.yihu.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PracticeParcel implements Parcelable {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":5077,"co_id":2,"ch_id":40,"zj_id":35,"bar_id":37,"name":"关于护理程序的概念，描述正确的是","A":"一种护理工作的分工类型","B":"一种护理工作的简化形式","C":"一种系统的解决问题的方法\t","D":"一种护理操作的模式","E":"一种护理操作的模式","correct":"B","analysis":"没有","image":"","sort":0,"open":1,"addtime":1523863201,"uptime":1523863802,"chaptername":"护士执业资格考试"},{"id":5078,"co_id":2,"ch_id":40,"zj_id":35,"bar_id":37,"name":"护理程序的理论基础不包括","A":"系统论","B":"解决问题论 ","C":"压力适应论","D":"信息交流论","E":"人的基本需要层次论","correct":"B","analysis":"没有","image":"","sort":0,"open":1,"addtime":1523863259,"uptime":1523863814,"chaptername":"护士执业资格考试"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 5077
         * co_id : 2
         * ch_id : 40
         * zj_id : 35
         * bar_id : 37
         * name : 关于护理程序的概念，描述正确的是
         * A : 一种护理工作的分工类型
         * B : 一种护理工作的简化形式
         * C : 一种系统的解决问题的方法
         * D : 一种护理操作的模式
         * E : 一种护理操作的模式
         * correct : B
         * analysis : 没有
         * image :
         * sort : 0
         * open : 1
         * addtime : 1523863201
         * uptime : 1523863802
         * chaptername : 护士执业资格考试
         */

        private int id;
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
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeTypedList(this.data);
    }

    public PracticeParcel() {
    }

    protected PracticeParcel(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<PracticeParcel> CREATOR = new Parcelable.Creator<PracticeParcel>() {
        @Override
        public PracticeParcel createFromParcel(Parcel source) {
            return new PracticeParcel(source);
        }

        @Override
        public PracticeParcel[] newArray(int size) {
            return new PracticeParcel[size];
        }
    };
}
