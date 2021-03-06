package com.botian.yihu.database;

import org.litepal.crud.DataSupport;

//litepal数据库关系模型对象
public class NoteData extends DataSupport {
    /**
     * id : 5110
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
    private int topicId;
    private String name;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String correct;
    private String analysis;
    private String image;
    private String note;
    private int cl;
    private int zhenti;
    private int typeid;
    private String material;
    private int judge;

    public int getJudge() {
        return judge;
    }

    public void setJudge(int judge) {
        this.judge = judge;
    }

    public int getZhenti() {
        return zhenti;
    }

    public void setZhenti(int zhenti) {
        this.zhenti = zhenti;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
