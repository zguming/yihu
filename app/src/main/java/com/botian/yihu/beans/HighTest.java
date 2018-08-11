package com.botian.yihu.beans;

import java.util.List;

public class HighTest {

    /**
     * code : 200
     * msg : 操作成功
     * invalidFilter : []
     * data : [{"id":27,"mid":1,"mids":2,"title":"患者，女性，70岁。现胃大部切除术后第3天，体温39.2℃。在护理患者的过程中，属于独立性护理措施的是","A":"遵医嘱发退热药","B":"用温水帮患者擦浴","C":"通知营养科调整患者饮食","D":"开放静脉通道，静脉点滴抗生素","E":"检查血常规，看白细胞数量","correct":"A","analysis":"护理措施分为：①依赖性护理措施，指的是护士遵医嘱执行的具体措施。②独立性护理措施，是指护士在职责范围内，根据搜集的资料，经过独立思考、判断所决定的措施。③协作性护理措施，即护士与其他医务人员之间合作完成的护理活动。故答案选A。","litpic":"/uploads/image/20180809/b79a688e354a7db12171f646244083b3.jpg","status":1,"create_time":"1970-01-01 08:00:00","update_time":"2018-08-09 10:27:22"},{"id":28,"mid":1,"mids":3,"title":"患者，男性，65岁。高血压病史30年，因情绪激动，呼吸急促，左胸部剧烈疼痛，以\u201c急性心肌梗死\u201d收住院。对该患者的护理，属于依赖性护理措施的是","A":"遵医嘱应用止痛药","B":"嘱患者绝对卧床休息","C":"观察吸氧后的病情变化","D":"通知营养科调整患者饮食","E":"安定患者情绪，进行心理护理","correct":"A","analysis":"护理措施分为：①依赖性护理措施，指的是护士遵医嘱执行的具体措施。②独立性护理措施，是指护士在职责范围内，根据搜集的资料，经过独立思考、判断所决定的措施。③协作性护理措施，即护士与其他医务人员之间合作完成的护理活动。故答案选A。","litpic":"","status":1,"create_time":"1970-01-01 08:00:00","update_time":"2018-08-09 09:32:18"}]
     */

    private int code;
    private String msg;
    private List<?> invalidFilter;
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

    public List<?> getInvalidFilter() {
        return invalidFilter;
    }

    public void setInvalidFilter(List<?> invalidFilter) {
        this.invalidFilter = invalidFilter;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 27
         * mid : 1
         * mids : 2
         * title : 患者，女性，70岁。现胃大部切除术后第3天，体温39.2℃。在护理患者的过程中，属于独立性护理措施的是
         * A : 遵医嘱发退热药
         * B : 用温水帮患者擦浴
         * C : 通知营养科调整患者饮食
         * D : 开放静脉通道，静脉点滴抗生素
         * E : 检查血常规，看白细胞数量
         * correct : A
         * analysis : 护理措施分为：①依赖性护理措施，指的是护士遵医嘱执行的具体措施。②独立性护理措施，是指护士在职责范围内，根据搜集的资料，经过独立思考、判断所决定的措施。③协作性护理措施，即护士与其他医务人员之间合作完成的护理活动。故答案选A。
         * litpic : /uploads/image/20180809/b79a688e354a7db12171f646244083b3.jpg
         * status : 1
         * create_time : 1970-01-01 08:00:00
         * update_time : 2018-08-09 10:27:22
         */

        private int id;
        private int mid;
        private int mids;
        private String title;
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String correct;
        private String analysis;
        private String litpic;
        private int status;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getMids() {
            return mids;
        }

        public void setMids(int mids) {
            this.mids = mids;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
