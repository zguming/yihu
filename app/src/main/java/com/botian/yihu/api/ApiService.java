package com.botian.yihu.api;

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
import com.botian.yihu.beans.MyNoteSection;
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

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public interface ApiService {
    //发送验证码
    @POST("api/Usres/sendsms")
    Observable<RegisterBean> getIdentify(@Query("mobile") String moblie);
    //注册
    @POST("api/Usres/sendcode")
    Observable<RegisterBean> register(@Query("username") String username, @Query("mobile") String moblie, @Query("password") String pwd, @Query("version") String version, @Query("code") String code);
    //登陆
    @POST("api/Usres/login")
    Observable<LoginBean> login(@Query("moblie") String phone, @Query("password") String pwd);
    //修改密码
    @POST("api/Usres/forget")
    Observable<RegisterBean> forget(@Query("moblie") String phone, @Query("code") String mobilelz, @Query("password") String pwd, @Query("password2") String pwd2);
    //发送版本信息
    @GET("api/version/version_updated")
    Observable<Version> getVersion(@Query("id") String id, @Query("version") String version);
    //get请求科目
    @GET("api/Column")
    Observable<SubjectBean> getSubject(@Query("noPage") String noPage);
    @GET("api/Column/video_sort/co_id/2/video_id/10")
    Observable<VideoListBean> getVideoList();
    //得到章节一级列表
    @GET("api/Column/chapterone")
    Observable<ChapterPracticeOneBean> getChapterPracticeOne(@Query("mid")String co_id);
    //得到章节列表
    @GET("api/chapter")
    Observable<ChapterPracticeListBean> getChapterPracticeList(@Query("noPage") String noPage, @Query("mid")String mid);
    //得到章节题
    @GET("api/section")
    Observable<PracticeAnswer> getPracticeAnswer(@Query("noPage") String noPage, @Query("typeid")String typeid);
    //得到章节材料标题
    @GET("api/material")
    Observable<Material> getPracticeMterial(@Query("noPage") String noPage, @Query("typeid")String typeid);
    //得到章节材料题
    @GET("api/stuff")
    Observable<PracticeAnswer> getPracticeStuff(@Query("noPage") String noPage, @Query("typeid")String typeid);
    //得到章节二级列表
    @GET("api/Column/chaptertwo")
    Observable<ChapterPracticeTwoBean> getChapterPracticeTwo(@Query("co_id")String co_id,@Query("id")String id);
    //得到章节special列表
    @GET("api/Column/chaptertwo")
    Observable<ChapterPracticeOneBean> getChapterPracticeSpecial(@Query("co_id")String co_id,@Query("id")String id);
    //得到护士执业章节题
    @GET("api/Column/Topic")
    Observable<PracticeBean> getChapterPractice(@Query("co_id")String co_id,@Query("ch_id")String ch_id,@Query("zj_id")String zj_id,@Query("bar_id")String bar_id);
    //显示评论
    @GET("api/review")
    Observable<OtherCommentBean> getComment(@Query("filter[]")String filter1,@Query("addon")String usres,@Query("page")String page,@Query("limit")String limit);
    //评论赞
    @POST("api/praise/save")
    Observable<ZanBean> getCommentZan(@Query("typeid")String mid,@Query("userid")String userid,@Query("mid")String typeid,@Query("iscai")String iscai);
    //题赞
    @POST("api/laud/save")
    Observable<ZanBean> getTiZan(@Query("mid")String mid,@Query("userid")String userid,@Query("cl")String cl,@Query("iscai")String iscai);
    //题赞数量
    @POST("api/laud")
    Observable<ZanBean> getTiZanNum(@Query("mid")String mid,@Query("userid")String userid,@Query("cl")String cl,@Query("iscai")String iscai);
    //发表评论
    @POST("api/review/save")
    Observable<SendCommentBean> getSendComment(@Query("userid") String user_id,@Query("mid") String topic_id,@Query("content") String content,@Query("cl") String cl);
    //新增笔记
    @POST("api/bj/save")
    Observable<No> addNote(@Query("mid")String mid, @Query("userid")String userid, @Query("content")String content, @Query("cl")String cl);
    //笔记列表
    @POST("api/bj")
    Observable<MyNoteSection> NoteList(@Query("addon")String addon, @Query("filter[]")String filter);
    //报错
    @FormUrlEncoded
    @POST("api/Comment/mistake")
    Observable<MistakeBean> getMistake(@Field("user_id") String user_id, @Field("userid") String userid, @Field("content") String content);
    //收藏
    @GET("api/sc/save")
    Observable<CollectionBean> getCollection(@Query("mid")String mid, @Query("userid")String userid, @Query("cl")String cl);
    //查看收藏
    @GET("api/sc")
    Observable<MyCollection> getCollectionRecords(@Query("noPage")String noPage,@Query("addon")String addon,@Query("filter[]")String filter);
    //清空收藏
    @GET("api/Share/Collection_Dell")
    Observable<CollectionDellBean> getCollectionDell(@Query("user_id")String user_id);
}