package com.botian.yihu.api;

import com.botian.yihu.beans.Adlist;
import com.botian.yihu.beans.ChangeUserInfo;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.beans.GetNewsComment;
import com.botian.yihu.beans.HighTest;
import com.botian.yihu.beans.KaoQianBuy;
import com.botian.yihu.beans.KaoQianClTitle;
import com.botian.yihu.beans.KaoQianNormal;
import com.botian.yihu.beans.KaoQianYaTiList;
import com.botian.yihu.beans.Live;
import com.botian.yihu.beans.LoginBean;
import com.botian.yihu.beans.Material;
import com.botian.yihu.beans.MistakeBean;
import com.botian.yihu.beans.MoniBuy;
import com.botian.yihu.beans.MoniCl;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.MoniTopic;
import com.botian.yihu.beans.MyMoney;
import com.botian.yihu.beans.NewsLable;
import com.botian.yihu.beans.NewsList;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.PayBeans;
import com.botian.yihu.beans.PayOrder;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.beans.RegisterBean;
import com.botian.yihu.beans.SearchKaoQianBuy;
import com.botian.yihu.beans.SearchMoniBuy;
import com.botian.yihu.beans.SendCommentBean;
import com.botian.yihu.beans.SendNewsComment;
import com.botian.yihu.beans.ShareList;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.beans.TopicCommentZip;
import com.botian.yihu.beans.UploadPhoto;
import com.botian.yihu.beans.Version;
import com.botian.yihu.beans.VideoBuy;
import com.botian.yihu.beans.VideoCataLog;
import com.botian.yihu.beans.VideoCatalog2;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.beans.VideoClassZip;
import com.botian.yihu.beans.VideoComment;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.VodeoBuyList;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.beans.ZanNum;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.GetMac;
import com.botian.yihu.util.GetMd5;
import com.botian.yihu.util.MyApplication;
import com.botian.yihu.util.SubjectUtil;
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
import okhttp3.MultipartBody;
import retrofit2.http.Part;

public class ApiMethods {

    /**
     * 封装线程管理和订阅的过程 通用
     */
    private static void ApiSubscribe(Observable observable, Observer observer, io.reactivex.ObservableTransformer style) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(style)
                .subscribe(observer);
    }

    //zip 操作符，实现多个接口数据共同更新 UI,PracticeAnswerActivity专用
    private static void ApiSubscribeZip(Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        final String[] cailiao = new String[1];
        final PracticeAnswer practiceAns = new PracticeAnswer();
        final List<PracticeAnswer.DataBean> practiceList = new ArrayList<>();
        Observable.zip(observable1, observable2, new BiFunction<PracticeAnswer, Material, List<Material.DataBean>>() {
            @Override
            public List<Material.DataBean> apply(PracticeAnswer practiceAnswer, Material material) throws Exception {
                practiceList.addAll(practiceAnswer.getData());
                practiceAns.setData(practiceList);
                List<Material.DataBean> list = new ArrayList<>();

                return material.getData();

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
                        return Api.getApiService().getPracticeStuff("", "typeid,eq," + material.getId(), "status,eq,1", GetMd5.md5())
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
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //zip 操作符，实现多个接口数据共同更新 UI,VideoFragment专用
    private static void ApiVideoClassZip(Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        Observable.zip(observable1, observable2, new BiFunction<Adlist, VideoClass, VideoClassZip>() {
            @Override
            public VideoClassZip apply(Adlist adlist, VideoClass videoClass) throws Exception {
                VideoClassZip videoClassZip = new VideoClassZip();
                videoClassZip.setData(videoClass.getData());
                videoClassZip.setData2(adlist.getData());

                return videoClassZip;

            }
        })
                .subscribeOn(Schedulers.io())
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    //题评论专用
    private static void ApiTopicZip(Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        Observable.zip(observable1, observable2, new BiFunction<ZanNum, OtherCommentBean, TopicCommentZip>() {
            @Override
            public TopicCommentZip apply(ZanNum zanNum, OtherCommentBean otherCommentBean) throws Exception {
                TopicCommentZip topicCommentZip = new TopicCommentZip();
                topicCommentZip.setData(zanNum.getData());
                topicCommentZip.setData2(otherCommentBean.getData());

                return topicCommentZip;

            }
        })
                .subscribeOn(Schedulers.io())
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //ActivityVideo专用
    private static void ApiVideo(Observable observable, Observer observer, io.reactivex.ObservableTransformer style) {
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function<VideoCatalog2, ObservableSource<VideoInfo>>() {

                    @Override
                    public ObservableSource<VideoInfo> apply(VideoCatalog2 videoCataLog) throws Exception {
                        String typeid = videoCataLog.getData().getData().get(0).getId() + "";
                        return Api.getApiService().getVideoInfo("typeid,eq," + typeid, "status,eq,1", GetMd5.md5());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(style)
                .subscribe(observer);
    }

    //zip 操作符，实现多个接口数据共同更新 UI,SimulationTestActivity2专用
    private static void ApiSubscribeZip2(final String filter, Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        final MoniTopic[] moniTopic1 = {new MoniTopic()};
        final List<PracticeAnswer.DataBean> practiceList = new ArrayList<>();
        Observable.zip(observable1, observable2, new BiFunction<MoniTopic, MoniCl, MoniCl>() {
            @Override
            public MoniCl apply(MoniTopic moniTopic, MoniCl moniCl) throws Exception {

                List<Material.DataBean> list = new ArrayList<>();
                moniTopic1[0] = moniTopic;
                return moniCl;

            }
        })
                .flatMap(new Function<MoniCl, ObservableSource<MoniCl.DataBean>>() {

                    @Override
                    public ObservableSource<MoniCl.DataBean> apply(MoniCl moniCl1) throws Exception {
                        return Observable.fromIterable(moniCl1.getData());
                    }
                }).subscribeOn(Schedulers.io())
                .flatMap(new Function<MoniCl.DataBean, ObservableSource<MoniTopic>>() {
                    @Override
                    public ObservableSource<MoniTopic> apply(MoniCl.DataBean material) throws Exception {
                        final String cailiao = material.getTitle();
                        //Log.d("所在的线程1：",Thread.currentThread().getName());
                        return Api.getApiService().getMoniTestCl(filter, "typeid,eq," + material.getId(), "noPage", "status,eq,1", GetMd5.md5())
                                .subscribeOn(Schedulers.io())
                                .map(new Function<MoniTopic, MoniTopic>() {
                                    @Override
                                    public MoniTopic apply(MoniTopic moniTopic) throws Exception {
                                        List<MoniTopic.DataBean> list = new ArrayList<>();
                                        list.addAll(moniTopic.getData());
                                        for (int i = 0; i < list.size(); i++) {
                                            list.get(i).setTitlecl(cailiao);
                                        }
                                        List<MoniTopic.DataBean> list3 = new ArrayList<>();
                                        list3.addAll(moniTopic1[0].getData());
                                        list3.addAll(list);
                                        moniTopic1[0].setData(list3);
                                        return moniTopic1[0];

                                    }
                                });
                    }
                })
                .defaultIfEmpty(moniTopic1[0])
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //zip 操作符，实现多个接口数据共同更新 UI,考前押题专用
    private static void ApiSubscribeZip3( Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        final KaoQianNormal[] moniTopic1 = {new KaoQianNormal()};
        final List<PracticeAnswer.DataBean> practiceList = new ArrayList<>();
        Observable.zip(observable1, observable2, new BiFunction<KaoQianNormal, KaoQianClTitle, KaoQianClTitle>() {
            @Override
            public KaoQianClTitle apply(KaoQianNormal moniTopic, KaoQianClTitle moniCl) throws Exception {

                List<Material.DataBean> list = new ArrayList<>();
                moniTopic1[0] = moniTopic;
                //Log.d("TAG", "apply: yyy");
                return moniCl;

            }
        })
                .flatMap(new Function<KaoQianClTitle, ObservableSource<KaoQianClTitle.DataBean>>() {

                    @Override
                    public ObservableSource<KaoQianClTitle.DataBean> apply(KaoQianClTitle moniCl1) throws Exception {
                        return Observable.fromIterable(moniCl1.getData());
                    }
                }).subscribeOn(Schedulers.io())
                .flatMap(new Function<KaoQianClTitle.DataBean, ObservableSource<KaoQianNormal>>() {
                    @Override
                    public ObservableSource<KaoQianNormal> apply(KaoQianClTitle.DataBean material) throws Exception {
                        final String cailiao = material.getTitle();
                        //Log.d("所在的线程1：",Thread.currentThread().getName());
                        return Api.getApiService().getKaoQianStuff("mids,eq,"+SubjectUtil.getSubjectNo2(), "typeid,eq," + material.getId(), "noPage", "status,eq,1", GetMd5.md5())
                                .subscribeOn(Schedulers.io())
                                .map(new Function<KaoQianNormal, KaoQianNormal>() {
                                    @Override
                                    public KaoQianNormal apply(KaoQianNormal moniTopic) throws Exception {
                                        List<KaoQianNormal.DataBean> list = new ArrayList<>();
                                        list.addAll(moniTopic.getData());
                                        for (int i = 0; i < list.size(); i++) {
                                            list.get(i).setTitlecl(cailiao);
                                        }
                                        List<KaoQianNormal.DataBean> list3 = new ArrayList<>();
                                        list3.addAll(moniTopic1[0].getData());
                                        list3.addAll(list);
                                        moniTopic1[0].setData(list3);
                                        return moniTopic1[0];

                                    }
                                });
                    }
                })
                .defaultIfEmpty(moniTopic1[0])
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用于获取验证码
     */
    public static void getIdentify(ProgressObserver<RegisterBean> observer, String mobile, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getIdentify(mobile), observer, yy.<RegisterBean>bindToLifecycle());
    }

    /**
     * 用于注册
     */
    public static void register(ProgressObserver<RegisterBean> observer, String username, String mobile, String pwd, String code, RxAppCompatActivity yy) {
        String mac = GetMac.getMac(MyApplication.getContext());
        ApiSubscribe(Api.getApiService().register(username, mobile, pwd, code, mac, "1"), observer, yy.<RegisterBean>bindToLifecycle());
    }

    /**
     * 用于登录
     */
    public static void login(ProgressObserver<LoginBean> observer, String phone, String pwd, RxAppCompatActivity yy) {
        String mac = GetMac.getMac(MyApplication.getContext());
        ApiSubscribe(Api.getApiService().login(phone, pwd, mac, "1"), observer, yy.<LoginBean>bindToLifecycle());
    }

    /**
     * 用于修改密码
     */
    public static void forget(ProgressObserver<RegisterBean> observer, String phone, String mobilelz, String pwd, String pwd2, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().forget(phone, mobilelz, pwd, pwd2), observer, yy.<RegisterBean>bindToLifecycle());
    }

    /**
     * 用于修改个人资料
     */
    public static void changeUserInfo(ProgressObserver<ChangeUserInfo> observer, String id, String username, String sex, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().changeUserInfo(id, username, sex), observer, yy.<ChangeUserInfo>bindToLifecycle());
    }

    /**
     * 用于更新版本信息
     */
    public static void getVersion(MyObserver<Version> observer, String version, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVersion(version), observer, yy.<Version>bindToLifecycle());
    }

    /**
     * 用于上传用户头像
     */
    public static void getPhoto(ProgressObserver<UploadPhoto> observer, String id, @Part MultipartBody.Part file, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getPhoto(id, file), observer, yy.<UploadPhoto>bindToLifecycle());
    }

    /**
     * 用于请求科目
     */
    public static void getSubject(ProgressObserver<SubjectBean> observer, RxAppCompatActivity yy) {
        String md5 = GetMd5.md5();
        //Log.d("TAG", "getSubject: "+md5);
        ApiSubscribe(Api.getApiService().getSubject("sorts,asc","", "status,eq,1", md5), observer, yy.<SubjectBean>bindToLifecycle());
    }

    /**
     * 用于请求章节练习列表
     */
    public static void getChapterPracticeList(ProgressObserver<ChapterPracticeListBean> observer, String mid, String mid2, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api7Days.getApiService().getChapterPracticeList("sorts,asc","", mid, mid2, filter, "status,eq,1", GetMd5.md5()), observer, yy.<ChapterPracticeListBean>bindToLifecycle());
    }

    /**
     * 用于请求章节练习题&章节练习材料标题
     */
    public static void getPracticeAnswer(ProgressObserver<PracticeAnswer> observer, String typeid, RxAppCompatActivity yy) {
        ApiSubscribeZip(Api7Days.getApiService().getPracticeAnswer("", typeid, "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), Api.getApiService().getPracticeMterial("", typeid, "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), observer, yy.<PracticeAnswer>bindToLifecycle());
    }

    /**
     * 用于发表评论
     */
    public static void getSendComment(ProgressObserver<SendCommentBean> observer, String user_id, String topic_id, String content, String cl, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getSendComment(user_id, topic_id, content, cl), observer, yy.<SendCommentBean>bindToLifecycle());
    }

    /**
     * 用于显示评论
     */
    public static void getComment(MyObserver<OtherCommentBean> observer, String filter1, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getComment(filter1, "usres", page, limit, "id,desc", GetMd5.md5()), observer, yy.<OtherCommentBean>bindToLifecycle());
    }

    /**
     * 用于评论赞
     */
    public static void getCommentZan(MyObserver<ZanBean> observer, String mid, String userid, String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCommentZan(mid, userid, iscai), observer, yy.<ZanBean>bindToLifecycle());
    }

    /**
     * 用于题赞
     */
    public static void getTiZan(MyObserver<ZanBean> observer, String mid, String userid, String cl, String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getTiZan(mid, userid, cl, iscai), observer, yy.<ZanBean>bindToLifecycle());
    }

    /**
     * 用于请求题评论，赞的数量
     */
    public static void getTopicCommentZip(ProgressObserver<TopicCommentZip> observer, String filter, RxAppCompatActivity yy) {
        ApiTopicZip(Api.getApiService().getTiZanNum(filter, GetMd5.md5()).subscribeOn(Schedulers.io()), Api.getApiService().getComment(filter, "usres", "1", "30", "id,desc", GetMd5.md5()).subscribeOn(Schedulers.io()), observer, yy.<TopicCommentZip>bindToLifecycle());
    }

    /**
     * 用于增加笔记
     */
    public static void addNote(MyObserver<No> observer, String mid, String userid, String content, String cl, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().addNote(mid, userid, content, cl), observer, yy.<No>bindToLifecycle());
    }

    /**
     * 用于意见反馈
     */
    public static void getMistake(ProgressObserver<MistakeBean> observer, String user_id, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMistake(user_id, content), observer, yy.<MistakeBean>bindToLifecycle());
    }

    /**
     * 收藏
     */
    //public static void getCollection(MyObserver<CollectionBean> observer, String mid, String userid, String cl,RxAppCompatActivity yy) {
    //ApiSubscribe(Api.getApiService().getCollection(mid, userid,cl), observer, yy);
    //}

    /**
     * 查看收藏
     */
    //public static void getCollectionRecords(ProgressObserver<MyCollection> observer, String filter, RxAppCompatActivity yy) {
    //ApiSubscribe(Api.getApiService().getCollectionRecords("noPage","section",filter), observer, yy);
    //}
    /**
     * 查看收藏材料标题
     */
    //public static void getCollectionMaterialTitle(ProgressObserver<MyCollection> observer, String filter2, String page,RxAppCompatActivity yy) {
    //ApiSubscribe(Api.getApiService().getCollectionMaterialTitle("material","cl,eq,1",filter2,page,"5"), observer, yy);
    //}
    /**
     * 清空收藏
     */
    //public static void getCollectionDell(ProgressObserver<CollectionDellBean> observer, String user_id, RxAppCompatActivity yy) {
    //ApiSubscribe(Api.getApiService().getCollectionDell(user_id), observer, yy);
    //}

    /**
     * 视频分类浏览次数
     */
    public static void getVideoClassNum(MyObserver<No> observer, String mid, String userid, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoClassNum(mid, userid), observer, yy.<No>bindToLifecycle());
    }

    /**
     * 用于请求视频分类 轮播图
     */
    public static void getVideoZip(ProgressObserver<VideoClassZip> observer, String filter, String filter2, String filter3, RxAppCompatActivity yy) {
        ApiVideoClassZip(Api.getApiService().getAdlist("noPage", filter, "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), Api.getApiService().getVideoClass("sorts,asc","noPage", filter2, filter3, "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), observer, yy.<VideoClassZip>bindToLifecycle());
    }

    /**
     * 视频目录
     */
    public static void getVideoDirectory(ProgressObserver<VideoCataLog> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoDirectory("sorts,asc","noPage", filter, "status,eq,1", GetMd5.md5()), observer, yy.<VideoCataLog>bindToLifecycle());
    }

    /**
     * 视频目录第一条
     */
    public static void getVideoDirectory1(ProgressObserver<VideoInfo> observer, String filter, String filter2, RxAppCompatActivity yy) {
        ApiVideo(Api.getApiService().getVideoDirectory1("sorts,asc",filter, filter2, "1", "1", "status,eq,1", GetMd5.md5()), observer, yy.<VideoInfo>bindToLifecycle());
    }

    /**
     * 视频数据
     */
    public static void getVideoInfo(MyObserver<VideoInfo> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoInfo(filter, "status,eq,1", GetMd5.md5()), observer, yy.<VideoInfo>bindToLifecycle());
    }

    /**
     * 视频评论列表
     */
    public static void getVideoCommentList(ProgressObserver<VideoComment> observer, String filter, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentList(filter, "usres", page, limit, "id,desc", GetMd5.md5()), observer, yy.<VideoComment>bindToLifecycle());
    }

    /**
     * 视频评论列表2
     */
    public static void getVideoCommentList2(MyObserver<VideoComment> observer, String filter, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentList(filter, "usres", page, limit, "id,desc", GetMd5.md5()), observer, yy.<VideoComment>bindToLifecycle());
    }

    /**
     * 视频评论点赞
     */
    public static void getVideoCommentZan(MyObserver<ZanBean> observer, String mid, String userid, String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentZan(mid, userid, iscai, GetMd5.md5()), observer, yy.<ZanBean>bindToLifecycle());
    }

    /**
     * 用于发表视频评论
     */
    public static void sendVideoComment(ProgressObserver<SendCommentBean> observer, String user_id, String topic_id, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendVideoComment(user_id, topic_id, content), observer, yy.<SendCommentBean>bindToLifecycle());
    }

    /**
     * 轮播图
     */
    public static void getAdlist(MyObserver<Adlist> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getAdlist("noPage", filter, "status,eq,1", GetMd5.md5()), observer, yy.<Adlist>bindToLifecycle());
    }

    /**
     * 资讯列表
     */
    public static void getNewsList(MyObserver<NewsList> observer, String filter3, String filter, String filter2, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewsList(filter3, filter, filter2, page, limit, "id,desc", "status,eq,1", GetMd5.md5()), observer, yy.<NewsList>bindToLifecycle());
    }

    /**
     * 资讯评论列表
     */
    public static void getNewComment(ProgressObserver<GetNewsComment> observer, String filter, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewComment(filter, "usres", page, limit, "id,desc", GetMd5.md5()), observer, yy.<GetNewsComment>bindToLifecycle());
    }

    /**
     * 资讯评论列表2
     */
    public static void getNewComment2(MyObserver<GetNewsComment> observer, String filter, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewComment(filter, "usres", page, limit, "id,desc", GetMd5.md5()), observer, yy.<GetNewsComment>bindToLifecycle());
    }

    /**
     * 资讯发表评论
     */
    public static void sendNewComment(ProgressObserver<SendNewsComment> observer, String mid, String userid, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendNewComment(mid, userid, content), observer, yy.<SendNewsComment>bindToLifecycle());
    }

    /**
     * 资讯标签
     */
    public static void getNewsLable(MyObserver<NewsLable> observer, String filter, String filter2, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewsLable("sorts,asc","noPage", filter, filter2, "status,eq,1", GetMd5.md5()), observer, yy.<NewsLable>bindToLifecycle());
    }

    /**
     * 模拟考试场地
     */
    public static void getMoniTestPlace(ProgressObserver<MoniTest> observer,  RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMoniTestPlace("sorts,asc","noPage", "mids,eq,"+SubjectUtil.getSubjectNo2(), "status,eq,1", GetMd5.md5()), observer, yy.<MoniTest>bindToLifecycle());
    }

    /**
     * 模拟考试交卷
     */
    public static void postMoni(MyObserver<No> observer, String userid, String overtine, String fartion, String correct, String wrong, String corrects, String wrongs, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().postMoni(userid, overtine, fartion, correct, wrong, corrects, wrongs), observer, yy.<No>bindToLifecycle());
    }

    /**
     * 用于请求模拟机考练习题&材料标题
     */
    public static void getMoniTestTopic(ProgressObserver<MoniTopic> observer, String filter1, String filter2, RxAppCompatActivity yy) {
        ApiSubscribeZip2(filter1, Api.getApiService().getMoniTest(filter1, filter2, "noPage", "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), Api.getApiService().getMoniCl(filter1, filter2, "noPage", "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), observer, yy.<MoniTopic>bindToLifecycle());
    }

    /**
     * 视频分类浏览次数
     */
    public static void sendNewNum(MyObserver<No> observer, String mid, String userid, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendNewNum(mid, userid), observer, yy.<No>bindToLifecycle());
    }

    /**
     * 提交分享数据
     */
    public static void sendShareData(MyObserver<No> observer, String userid, String chaprerid, String columnid, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendShareData(userid, chaprerid, columnid), observer, yy.<No>bindToLifecycle());
    }

    /**
     * 得到分享列表数据
     */
    public static void getShareData(MyObserver<ShareList> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getShareData("noPage", filter, GetMd5.md5()), observer, yy.<ShareList>bindToLifecycle());
    }

    /**
     * 提交订单
     */
    public static void payMoney(ProgressObserver<PayBeans> observer, String userId, String order, String account, String money, String payment, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().payMoney(userId, order, account, money, payment), observer, yy.<PayBeans>bindToLifecycle());
    }

    /**
     * 得到分享列表数据
     */
    public static void getPayOrder(ProgressObserver<PayOrder> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getPayOrder("noPage", filter, GetMd5.md5()), observer, yy.<PayOrder>bindToLifecycle());
    }

    /**
     * 购买视频
     */
    public static void videoBuy(ProgressObserver<VideoBuy> observer, String userid, String column_id, String video_id, String directory_id, String videodata_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().videoBuy(userid, column_id, video_id, directory_id, videodata_id), observer, yy.<VideoBuy>bindToLifecycle());
    }

    /**
     * 购买视频列表
     */
    public static void getVideoBuyList(MyObserver<VodeoBuyList> observer, String filter1, String filter2, String filter3, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoBuyList("noPage", filter1, filter2, filter3, GetMd5.md5()), observer, yy.<VodeoBuyList>bindToLifecycle());
    }

    /**
     * 购买视频列表2
     */
    public static void getVideoBuyList2(ProgressObserver<VodeoBuyList> observer, String filter1, String filter2, String filter3, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoBuyList("noPage", filter1, filter2, filter3, GetMd5.md5()), observer, yy.<VodeoBuyList>bindToLifecycle());
    }

    /**
     * 购买模拟考试
     */
    public static void moniBuy(ProgressObserver<MoniBuy> observer, String userid,  String cbt_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().moniBuy(userid, SubjectUtil.getSubjectNo2()+"", cbt_id), observer, yy.<MoniBuy>bindToLifecycle());
    }

    /**
     * 查询是否购买模拟考试
     */
    public static void searchMoniBuy(MyObserver<SearchMoniBuy> observer, String userid, String column_id, String cbt_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().searchMoniBuy(userid, column_id, cbt_id), observer, yy.<SearchMoniBuy>bindToLifecycle());
    }

    /**
     * 查询是否购买模拟考试2
     */
    public static void searchMoniBuy2(ProgressObserver<SearchMoniBuy> observer, String userid, String column_id, String cbt_id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().searchMoniBuy(userid, column_id, cbt_id), observer, yy.<SearchMoniBuy>bindToLifecycle());
    }

    /**
     * 我的余额
     */
    public static void getMyMoney(ProgressObserver<MyMoney> observer, String userid, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMyMoney(userid, GetMd5.md5()), observer, yy.<MyMoney>bindToLifecycle());
    }

    /**
     * 直播列表
     */
    public static void getLive(ProgressObserver<Live> observer, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getLive("sorts,asc","noPage", "Zhteacher", "status,eq,1", "column_id,eq," + SubjectUtil.getSubjectNo2(), GetMd5.md5()), observer, yy.<Live>bindToLifecycle());
    }

    /**
     * 高频考点
     */
    public static void getHighTest(ProgressObserver<HighTest> observer, String filter, String filter2, RxAppCompatActivity yy) {
        ApiSubscribe(Api1Days.getApiService().getHighTest("noPage", filter, filter2, "status,eq,1", GetMd5.md5()), observer, yy.<HighTest>bindToLifecycle());
    }

    /**
     * 考前押题列表
     */
    public static void getKaoQianList(ProgressObserver<KaoQianYaTiList> observer, RxAppCompatActivity yy) {
        ApiSubscribe(Api1Days.getApiService().getKaoQianList("sorts,asc","noPage", "mids,eq," + SubjectUtil.getSubjectNo2(), "status,eq,1", GetMd5.md5()), observer,  yy.<KaoQianYaTiList>bindToLifecycle());
    }

    /**
     * 考前押题购买
     */
    public static void KaoQianBuy(ProgressObserver<KaoQianBuy> observer, String userid, String id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().KaoQianBuy(userid, SubjectUtil.getSubjectNo2() + "", id), observer, yy.<KaoQianBuy>bindToLifecycle());
    }

    /**
     * 查询考前押题是否购买
     */
    public static void SearchKaoQianBuy(MyObserver<SearchKaoQianBuy> observer, String userid, String id, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().SearchKaoQianBuy(userid, SubjectUtil.getSubjectNo2() + "", id), observer, yy.<SearchKaoQianBuy>bindToLifecycle());
    }
    /**
     * 用于请求考前押题练习题&考前押题材料标题
     */
    public static void getKaoQianNormal(ProgressObserver<KaoQianNormal> observer, String typeid, RxAppCompatActivity yy) {
        ApiSubscribeZip3(Api1Days.getApiService().getKaoQianNormal("", "bet_id,eq,"+typeid, "mids,eq,"+SubjectUtil.getSubjectNo2(), "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), Api.getApiService().getKaoQianStuffTitle("", "bet_id,eq,"+typeid, "mids,eq,"+SubjectUtil.getSubjectNo2(), "status,eq,1", GetMd5.md5()).subscribeOn(Schedulers.io()), observer, yy.<KaoQianNormal>bindToLifecycle());
    }

}