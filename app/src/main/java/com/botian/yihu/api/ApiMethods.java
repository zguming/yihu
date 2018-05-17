package com.botian.yihu.api;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.beans.ChapterPracticeOneBean;
import com.botian.yihu.beans.ChapterPracticeTwoBean;
import com.botian.yihu.beans.CollectionBean;
import com.botian.yihu.beans.CollectionDellBean;
import com.botian.yihu.beans.CollectionRecordsBean;
import com.botian.yihu.beans.LoginBean;
import com.botian.yihu.beans.Material;
import com.botian.yihu.beans.MistakeBean;
import com.botian.yihu.beans.MyCollection;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.beans.PracticeBean;
import com.botian.yihu.beans.RegisterBean;
import com.botian.yihu.beans.SendCommentBean;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.beans.Version;
import com.botian.yihu.beans.VideoListBean;
import com.botian.yihu.beans.ZanBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {

    /**
     * 封装线程管理和订阅的过程 通用
     */
    private static void ApiSubscribe(Observable observable, Observer observer, RxAppCompatActivity rxApp) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxApp.<Long>bindToLifecycle())
                .subscribe(observer);
    }

    //zip 操作符，实现多个接口数据共同更新 UI,PracticeAnswerActivity专用
    private static void ApiSubscribeZip(Observable observable1, Observable observable2, final Observer observer, RxAppCompatActivity rxApp) {
        final String[] cailiao = new String[1];
        final PracticeAnswer practiceAns = new PracticeAnswer();
        final List<PracticeAnswer.DataBean> practiceList = new ArrayList<>();
        Observable.zip(observable1, observable2, new BiFunction<PracticeAnswer, Material, List<Material.DataBean>>() {
            @Override
            public List<Material.DataBean> apply(PracticeAnswer practiceAnswer, Material material) throws Exception {
                practiceList.addAll(practiceAnswer.getData());
                practiceAns.setData(practiceList);
                List<Material.DataBean> list = new ArrayList<>();
                /*Material.DataBean aaa = new Material.DataBean();
                aaa.setId(22);
                aaa.setTitle("你好啊啊 啊啊啊 啊");
                Material.DataBean aaa1 = new Material.DataBean();
                aaa1.setId(23);
                aaa1.setTitle("你好啊啊 啊啊啊 啊1111");
                Material.DataBean aaa2 = new Material.DataBean();
                aaa2.setId(24);
                aaa2.setTitle("你好啊啊 啊啊啊 啊2222");

                list.add(aaa);
                list.add(aaa1);
                list.add(aaa2);


                Log.d("所在的线程99：",Thread.currentThread().getName());
                material.setData(list);*/
                return material.getData();
                //return list;
            }
        })
                .flatMap(new Function<List<Material.DataBean>, ObservableSource<Material.DataBean>>() {

                    @Override
                    public ObservableSource<Material.DataBean> apply(List<Material.DataBean> material) throws Exception {
                        //Log.d("TAG", "apply:鱼和团额网站虾 ");
                        //Log.d("所在的线程0：",Thread.currentThread().getName());
                        return Observable.fromIterable(material);
                    }
                }).subscribeOn(Schedulers.io())
                .flatMap(new Function<Material.DataBean, ObservableSource<PracticeAnswer>>() {
                    @Override
                    public ObservableSource<PracticeAnswer> apply(Material.DataBean material) throws Exception {
                        final String cailiao = material.getTitle();
                        final int cl = material.getCl();
                        final int mid = material.getId();
                        //Log.d("所在的线程1：",Thread.currentThread().getName());
                        return Api.getApiService().getPracticeStuff("", material.getTypeid() + "")
                                .subscribeOn(Schedulers.io())
                                .map(new Function<PracticeAnswer, PracticeAnswer>() {
                                    @Override
                                    public PracticeAnswer apply(PracticeAnswer practiceAnswer) throws Exception {
                                        //Log.d("TAG", "apply:和 "+"");
                                        List<PracticeAnswer.DataBean> list = new ArrayList<>();
                                        list.addAll(practiceAnswer.getData());
                                        for (int i = 0; i < list.size(); i++) {
                                            list.get(i).setMaterial(cailiao);
                                            list.get(i).setCl(cl);
                                            list.get(i).setId(mid);
                                        }
                                        practiceList.addAll(list);
                                        practiceAns.setData(practiceList);
                                        //Log.d("所在的线程2：",Thread.currentThread().getName());
                                        return practiceAns;

                                    }
                                });
                    }
                })
                .defaultIfEmpty(practiceAns)
                .compose(rxApp.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 用于获取验证码
     */
    public static void getIdentify(ProgressObserver<RegisterBean> observer, String mobile, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getIdentify(mobile), observer, yy);
    }

    /**
     * 用于注册
     */
    public static void register(ProgressObserver<RegisterBean> observer, String username, String mobile, String pwd, String version, String code, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().register(username, mobile, pwd, version, code), observer, yy);
    }

    /**
     * 用于登录
     */
    public static void login(ProgressObserver<LoginBean> observer, String phone, String pwd, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().login(phone, pwd), observer, yy);
    }

    /**
     * 用于修改密码
     */
    public static void forget(ProgressObserver<RegisterBean> observer, String phone, String mobilelz, String pwd, String pwd2, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().forget(phone, mobilelz, pwd, pwd2), observer, yy);
    }
    /**
     * 用于更新版本信息
     */
    public static void getVersion(MyObserver<Version> observer, String id, String version, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVersion(id, version), observer, yy);
    }
    /**
     * 用于请求科目
     */
    public static void getSubject(ProgressObserver<SubjectBean> observer, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getSubject(""), observer, yy);
    }

    /**
     * 用于请求视频列表
     */
    public static void getVideoList(ProgressObserver<VideoListBean> observer, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoList(), observer, yy);
    }

    /**
     * 用于请求章节练习一级列表
     */
    public static void getChapterPracticeOne(ProgressObserver<ChapterPracticeOneBean> observer, String co_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getChapterPracticeOne(co_id), observer, yy);
    }

    /**
     * 用于请求章节练习列表
     */
    public static void getChapterPracticeList(ProgressObserver<ChapterPracticeListBean> observer, String mid, RxAppCompatActivity rxApp) {
        ApiSubscribe(Api.getApiService().getChapterPracticeList("", mid), observer, rxApp);
    }

    /**
     * 用于请求章节练习题&章节练习材料标题
     */
    public static void getPracticeAnswer(ProgressObserver<PracticeAnswer> observer, String typeid, RxAppCompatActivity rxApp) {
        ApiSubscribeZip(Api.getApiService().getPracticeAnswer("", typeid).subscribeOn(Schedulers.io()), Api.getApiService().getPracticeMterial("", typeid).subscribeOn(Schedulers.io()), observer, rxApp);
    }

    /**
     * 用于请求章节练习材料题
     */
    public static void getPracticeStuff(ProgressObserver<PracticeAnswer> observer, String typeid, RxAppCompatActivity rxApp) {
        //ApiSubscribeZip(Api.getApiService().getPracticeStuff("", typeid), observer, rxApp);
    }

    /**
     * 用于请求章节练习二级列表
     */
    public static void getChapterPracticeTwo(ProgressObserver<ChapterPracticeTwoBean> observer, String co_id, String id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getChapterPracticeTwo(co_id, id), observer, yy);
    }

    /**
     * 用于请求章节练习二级列表
     */
    public static void getChapterPracticeSpecial(ProgressObserver<ChapterPracticeOneBean> observer, String co_id, String id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getChapterPracticeSpecial(co_id, id), observer, yy);
    }

    /**
     * 用于请求章节练习护士执业题
     */
    public static void getChapterPractice(ProgressObserver<PracticeBean> observer, String co_id, String ch_id, String zj_id, String bar_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getChapterPractice(co_id, ch_id, zj_id, bar_id), observer, yy);
    }

    /**
     * 用于发表评论
     */
    public static void getSendComment(ProgressObserver<SendCommentBean> observer, String user_id, String topic_id, String content, String cl,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getSendComment(user_id, topic_id, content,cl), observer, yy);
    }

    /**
     * 用于显示评论
     */
    public static void getComment(ProgressObserver<OtherCommentBean> observer, String filter1, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getComment( filter1, "usres",page, limit), observer, yy);
    }

    /**
     * 用于评论赞
     */
    public static void getCommentZan(MyObserver<ZanBean> observer, String mid,String userid,String typeid,String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCommentZan(mid,userid,typeid,iscai), observer, yy);
    }
    /**
     * 用于题赞
     */
    public static void getTiZan(MyObserver<ZanBean> observer, String mid,String userid,String cl,String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getTiZan(mid,userid,cl,iscai), observer, yy);
    }
    /**
     * 用于题赞
     */
    public static void addNote(MyObserver<No> observer, String mid, String userid, String content,String cl, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().addNote(mid,userid,content,cl), observer, yy);
    }
    /**
     * 用于报告错题
     */
    public static void getMistake(ProgressObserver<MistakeBean> observer, String user_id, String topic_id, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMistake(user_id, topic_id, content), observer, yy);
    }

    /**
     * 收藏
     */
    public static void getCollection(MyObserver<CollectionBean> observer, String mid, String userid, String cl,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollection(mid, userid,cl), observer, yy);
    }

    /**
     * 查看收藏
     */
    public static void getCollectionRecords(ProgressObserver<MyCollection> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollectionRecords("noPage","section",filter), observer, yy);
    }

    /**
     * 清空收藏
     */
    public static void getCollectionDell(ProgressObserver<CollectionDellBean> observer, String user_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollectionDell(user_id), observer, yy);
    }
}