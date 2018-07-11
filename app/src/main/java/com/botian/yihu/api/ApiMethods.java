package com.botian.yihu.api;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.beans.Adlist;
import com.botian.yihu.beans.ChangeUserInfo;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.beans.GetNewsComment;
import com.botian.yihu.beans.LoginBean;
import com.botian.yihu.beans.Material;
import com.botian.yihu.beans.MistakeBean;
import com.botian.yihu.beans.MoniCl;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.MoniTopic;
import com.botian.yihu.beans.NewsLable;
import com.botian.yihu.beans.NewsList;
import com.botian.yihu.beans.NewsZip;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.beans.RegisterBean;
import com.botian.yihu.beans.SendCommentBean;
import com.botian.yihu.beans.SendNewsComment;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.beans.TopicCommentZip;
import com.botian.yihu.beans.UploadPhoto;
import com.botian.yihu.beans.Version;
import com.botian.yihu.beans.VideoCataLog;
import com.botian.yihu.beans.VideoCatalog2;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.beans.VideoClassZip;
import com.botian.yihu.beans.VideoComment;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.beans.ZanNum;
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
                .compose(style)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    //zip 操作符，实现多个接口数据共同更新 UI,VideoFragment专用
    private static void ApiVideoClassZip(Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        Observable.zip(observable1, observable2, new BiFunction<Adlist, VideoClass, VideoClassZip>() {
            @Override
            public VideoClassZip apply(Adlist adlist, VideoClass videoClass) throws Exception {
                VideoClassZip videoClassZip=new VideoClassZip();
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

    //NewsFragment专用
    private static void ApiNews(Observable observable, Observer observer, io.reactivex.ObservableTransformer style, final String filter, final String filter2) {
        final NewsLable[] newsLable3 = new NewsLable[1];
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function< NewsLable, ObservableSource<NewsList>>() {

                    @Override
                    public ObservableSource<NewsList> apply(NewsLable newsLable) throws Exception {
                        newsLable3[0] =newsLable;
                        String typeid=newsLable.getData().get(0).getId()+"";
                        return Api.getApiService().getNewsList("typeid,eq,"+typeid,filter,filter2,"1","20","id,desc");
                    }
                })
                .map(new Function<NewsList, NewsZip>() {
                    @Override
                    public NewsZip apply(NewsList newsList) throws Exception {

                        NewsZip newsZip=new NewsZip();
                        newsZip.setData(newsLable3[0].getData());
                        newsZip.setData2(newsList.getData());
                        return newsZip;

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(style)
                .subscribe(observer);
    }
    //题评论专用
    private static void ApiTopicZip(Observable observable1, Observable observable2, final Observer observer, io.reactivex.ObservableTransformer style) {
        Observable.zip(observable1, observable2, new BiFunction<ZanNum, OtherCommentBean, TopicCommentZip>() {
            @Override
            public TopicCommentZip apply(ZanNum zanNum, OtherCommentBean otherCommentBean) throws Exception {
                TopicCommentZip topicCommentZip=new TopicCommentZip();
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
                        String typeid=videoCataLog.getData().getData().get(0).getId()+"";
                        return Api.getApiService().getVideoInfo("typeid,eq," + typeid);
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
                moniTopic1[0] =moniTopic;
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
                        return Api.getApiService().getMoniTestCl(filter, "typeid,eq,"+material.getId(),"noPage")
                                .subscribeOn(Schedulers.io())
                                .map(new Function<MoniTopic, MoniTopic>() {
                                    @Override
                                    public MoniTopic apply(MoniTopic moniTopic) throws Exception {
                                        List<MoniTopic.DataBean> list = new ArrayList<>();
                                        list.addAll(moniTopic.getData());
                                        for (int i = 0; i < list.size(); i++) {
                                            list.get(i).setTitlecl(cailiao);
                                        }
                                        List<MoniTopic.DataBean> list3=new ArrayList<>();
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
    public static void register(ProgressObserver<RegisterBean> observer, String username, String mobile, String pwd, String version, String code, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().register(username, mobile, pwd, version, code), observer, yy.<RegisterBean>bindToLifecycle());
    }

    /**
     * 用于登录
     */
    public static void login(ProgressObserver<LoginBean> observer, String phone, String pwd, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().login(phone, pwd), observer, yy.<LoginBean>bindToLifecycle());
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
        ApiSubscribe(Api.getApiService().changeUserInfo(id,username,sex), observer, yy.<ChangeUserInfo>bindToLifecycle());
    }
    /**
     * 用于更新版本信息
     */
    public static void getVersion(MyObserver<Version> observer, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVersion(), observer, yy.<Version>bindToLifecycle());
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
        ApiSubscribe(Api.getApiService().getSubject(""), observer, yy.<SubjectBean>bindToLifecycle());
    }

    /**
     * 用于请求章节练习列表
     */
    public static void getChapterPracticeList(ProgressObserver<ChapterPracticeListBean> observer, String mid, String mid2,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getChapterPracticeList("", mid,mid2), observer,yy.<ChapterPracticeListBean>bindToLifecycle());
    }

    /**
     * 用于请求章节练习题&章节练习材料标题
     */
    public static void getPracticeAnswer(ProgressObserver<PracticeAnswer> observer, String typeid, RxAppCompatActivity yy) {
        ApiSubscribeZip(Api.getApiService().getPracticeAnswer("", typeid).subscribeOn(Schedulers.io()), Api.getApiService().getPracticeMterial("", typeid).subscribeOn(Schedulers.io()), observer, yy.<PracticeAnswer>bindToLifecycle());
    }

    /**
     * 用于发表评论
     */
    public static void getSendComment(ProgressObserver<SendCommentBean> observer, String user_id, String topic_id, String content, String cl,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getSendComment(user_id, topic_id, content,cl), observer, yy.<SendCommentBean>bindToLifecycle());
    }

    /**
     * 用于显示评论
     */
    public static void getComment(MyObserver<OtherCommentBean> observer, String filter1, String page, String limit, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getComment( filter1, "usres",page, limit,"id,desc"), observer, yy.<OtherCommentBean>bindToLifecycle());
    }

    /**
     * 用于评论赞
     */
    public static void getCommentZan(MyObserver<ZanBean> observer, String mid,String userid,String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getCommentZan(mid,userid,iscai), observer, yy.<ZanBean>bindToLifecycle());
    }
    /**
     * 用于题赞
     */
    public static void getTiZan(MyObserver<ZanBean> observer, String mid,String userid,String cl,String iscai, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getTiZan(mid,userid,cl,iscai), observer, yy.<ZanBean>bindToLifecycle());
    }
    /**
     * 用于请求题评论，赞的数量
     */
    public static void getTopicCommentZip(ProgressObserver<TopicCommentZip> observer, String filter, RxAppCompatActivity yy) {
        ApiTopicZip(Api.getApiService().getTiZanNum(filter).subscribeOn(Schedulers.io()), Api.getApiService().getComment( filter, "usres","1", "30","id,desc").subscribeOn(Schedulers.io()), observer, yy.<TopicCommentZip>bindToLifecycle());
    }
    /**
     * 用于增加笔记
     */
    public static void addNote(MyObserver<No> observer, String mid, String userid, String content,String cl, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().addNote(mid,userid,content,cl), observer, yy.<No>bindToLifecycle());
    }
    /**
     * 用于意见反馈
     */
    public static void getMistake(ProgressObserver<MistakeBean> observer, String user_id, String content, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMistake(user_id,  content), observer, yy.<MistakeBean>bindToLifecycle());
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
    public static void getVideoClassNum(MyObserver<No> observer, String mid,String userid,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoClassNum(mid,userid), observer, yy.<No>bindToLifecycle());
    }
    /**
     * 用于请求视频分类 轮播图
     */
    public static void getVideoZip(ProgressObserver<VideoClassZip> observer, String filter, String filter2, String filter3,RxAppCompatActivity yy) {
        ApiVideoClassZip(Api.getApiService().getAdlist("noPage",filter).subscribeOn(Schedulers.io()), Api.getApiService().getVideoClass("noPage",filter2,filter3).subscribeOn(Schedulers.io()), observer, yy.<VideoClassZip>bindToLifecycle());
    }
    /**
     * 视频目录
     */
    public static void getVideoDirectory(ProgressObserver<VideoCataLog> observer, String filter,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoDirectory("noPage",filter), observer, yy.<VideoCataLog>bindToLifecycle());
    }
    /**
     * 视频目录第一条
     */
    public static void getVideoDirectory1(ProgressObserver<VideoInfo> observer, String filter,String filter2, RxAppCompatActivity yy) {
        ApiVideo(Api.getApiService().getVideoDirectory1(filter,filter2,"1","1"), observer, yy.<VideoInfo>bindToLifecycle());
    }
    /**
     * 视频数据
     */
    public static void getVideoInfo(MyObserver<VideoInfo> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoInfo(filter), observer, yy.<VideoInfo>bindToLifecycle());
    }
    /**
     * 视频评论列表
     */
    public static void getVideoCommentList(ProgressObserver<VideoComment> observer, String filter, String page, String limit ,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentList(filter,"usres",page,limit,"id,desc"), observer, yy.<VideoComment>bindToLifecycle());
    }
    /**
     * 视频评论列表2
     */
    public static void getVideoCommentList2(MyObserver<VideoComment> observer, String filter, String page, String limit ,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentList(filter,"usres",page,limit,"id,desc"), observer, yy.<VideoComment>bindToLifecycle());
    }
    /**
     * 视频评论点赞
     */
    public static void getVideoCommentZan(MyObserver<ZanBean> observer, String mid,String userid,String iscai,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getVideoCommentZan(mid,userid,iscai), observer, yy.<ZanBean>bindToLifecycle());
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
    public static void getAdlist(MyObserver<Adlist> observer,String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getAdlist("noPage",filter), observer, yy.<Adlist>bindToLifecycle());
    }

    /**
     * 资讯列表
     */
    public static void getNewsList(ProgressObserver<NewsList> observer, String filter3,String filter,String filter2, String page, String limit , RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewsList(filter3,filter,filter2,page,limit,"id,desc"), observer, yy.<NewsList>bindToLifecycle());
    }
    /**
     * 资讯评论列表
     */
    public static void getNewComment(ProgressObserver<GetNewsComment> observer, String filter, String page, String limit , RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewComment(filter,"usres",page,limit,"id,desc"), observer, yy.<GetNewsComment>bindToLifecycle());
    }
    /**
     * 资讯评论列表2
     */
    public static void getNewComment2(MyObserver<GetNewsComment> observer, String filter, String page, String limit , RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getNewComment(filter,"usres",page,limit,"id,desc"), observer, yy.<GetNewsComment>bindToLifecycle());
    }
    /**
     * 资讯发表评论
     */
    public static void sendNewComment(ProgressObserver<SendNewsComment> observer, String mid, String userid, String content , RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendNewComment(mid,userid,content), observer, yy.<SendNewsComment>bindToLifecycle());
    }
    /**
     * 资讯标签
     */
    public static void getNewsLable(ProgressObserver<NewsList> observer, String filter, String filter2,RxAppCompatActivity yy) {
        ApiNews(Api.getApiService().getNewsLable("noPage",filter,filter2), observer, yy.<NewsList>bindToLifecycle(),filter,filter2);
    }
    /**
     * 模拟考试场地
     */
    public static void getMoniTestPlace(ProgressObserver<MoniTest> observer, String filter, RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().getMoniTestPlace("noPage",filter), observer, yy.<MoniTest>bindToLifecycle());
    }
    /**
     * 用于请求模拟机考练习题&材料标题
     */
    public static void getMoniTestTopic(ProgressObserver<MoniTopic> observer, String filter1, String filter2, RxAppCompatActivity yy) {
        ApiSubscribeZip2(filter1,Api.getApiService().getMoniTest(filter1,filter2,"noPage").subscribeOn(Schedulers.io()), Api.getApiService().getMoniCl(filter1, filter2,"noPage").subscribeOn(Schedulers.io()), observer, yy.<MoniTopic>bindToLifecycle());
    }
    /**
     * 视频分类浏览次数
     */
    public static void sendNewNum(MyObserver<No> observer, String mid,String userid,RxAppCompatActivity yy) {
        ApiSubscribe(Api.getApiService().sendNewNum(mid,userid), observer, yy.<No>bindToLifecycle());
    }
}