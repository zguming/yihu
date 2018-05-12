package com.botian.yihu.api;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.data.ChapterPracticeListBean;
import com.botian.yihu.data.ChapterPracticeOneBean;
import com.botian.yihu.data.ChapterPracticeTwoBean;
import com.botian.yihu.data.CollectionBean;
import com.botian.yihu.data.CollectionDellBean;
import com.botian.yihu.data.CollectionRecordsBean;
import com.botian.yihu.data.LoginBean;
import com.botian.yihu.data.MistakeBean;
import com.botian.yihu.data.OtherCommentBean;
import com.botian.yihu.data.PracticeAnswer;
import com.botian.yihu.data.PracticeBean;
import com.botian.yihu.data.RegisterBean;
import com.botian.yihu.data.SendCommentBean;
import com.botian.yihu.data.SubjectBean;
import com.botian.yihu.data.VideoListBean;
import com.botian.yihu.data.ZanBean;
import com.botian.yihu.data.Material;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
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
    private static void ApiSubscribeZip(Observable observable1, Observable observable2,Observer observer, RxAppCompatActivity rxApp) {
        Observable.zip(observable1, observable2, new BiFunction<PracticeAnswer, Material, Material>() {
            @Override
            public Material apply(PracticeAnswer practiceAnswer,  Material material) throws Exception {
                List<Material.DataBean> list = new ArrayList<>();

                Material.DataBean aaa=new Material.DataBean();
                aaa.setId(22);
                aaa.setLitpic("");
                aaa.setCreate_time("");
                aaa.setStatus(1);
                aaa.setTitle("");
                aaa.setTypeid(1);
                aaa.setUpdate_time("");
                list.add(aaa);
                list.add(aaa);
                list.add(aaa);
                list.add(aaa);
                material.setData(list);
                return material;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxApp.<Long>bindToLifecycle())
                .doOnNext(new Consumer<Material>() {
                    @Override
                    public void accept(Material material) throws Exception {

                    }
                })
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取材料题的请求
                .flatMap(new Function<Material, ObservableSource<PracticeAnswer>>() {

                    @Override
                    public ObservableSource<PracticeAnswer> apply( Material material) throws Exception {
                        if (material.getData().size()>0){
                                return Api.getApiService().getPracticeStuff("", material.getData().get(0).getId()+"");

                        }
                        return null;

                    }
                })
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
        ApiSubscribeZip(Api.getApiService().getPracticeAnswer("", typeid),Api.getApiService().getPracticeMterial("", typeid), observer, rxApp);
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
    public static void getSendComment(ProgressObserver<SendCommentBean> observer, String user_id, String topic_id, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getSendComment(user_id, topic_id, content), observer, yy);
    }

    /**
     * 用于显示评论
     */
    public static void getComment(ProgressObserver<OtherCommentBean> observer, String topic_id, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getComment(topic_id, page, limit), observer, yy);
    }

    /**
     * 用于显示评论赞
     */
    public static void getCommentZan(MyObserver<ZanBean> observer, String Comment, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCommentZan(Comment), observer, yy);
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
    public static void getCollection(MyObserver<CollectionBean> observer, String user_id, String topic_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollection(user_id, topic_id), observer, yy);
    }

    /**
     * 查看收藏
     */
    public static void getCollectionRecords(ProgressObserver<CollectionRecordsBean> observer, String user_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollectionRecords(user_id), observer, yy);
    }

    /**
     * 清空收藏
     */
    public static void getCollectionDell(ProgressObserver<CollectionDellBean> observer, String user_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCollectionDell(user_id), observer, yy);
    }
}