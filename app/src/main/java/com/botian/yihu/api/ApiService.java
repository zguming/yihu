package com.botian.yihu.api;

import com.botian.yihu.data.ChapterPracticeListBean;
import com.botian.yihu.data.ChapterPracticeOneBean;
import com.botian.yihu.data.ChapterPracticeTwoBean;
import com.botian.yihu.data.CollectionBean;
import com.botian.yihu.data.CollectionDellBean;
import com.botian.yihu.data.CollectionRecordsBean;
import com.botian.yihu.data.LoginBean;
import com.botian.yihu.data.Material;
import com.botian.yihu.data.MistakeBean;
import com.botian.yihu.data.OtherCommentBean;
import com.botian.yihu.data.PracticeAnswer;
import com.botian.yihu.data.PracticeBean;
import com.botian.yihu.data.RegisterBean;
import com.botian.yihu.data.SendCommentBean;
import com.botian.yihu.data.SubjectBean;
import com.botian.yihu.data.VideoListBean;
import com.botian.yihu.data.ZanBean;

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
    @POST("api/users/sendsms")
    Observable<RegisterBean> getIdentify(@Query("mobile") String moblie);
    //注册
    @POST("api/users/sendcode")
    Observable<RegisterBean> register(@Query("username") String username, @Query("mobile") String moblie, @Query("password") String pwd, @Query("version") String version, @Query("code") String code);
    //登陆
    @POST("api/users/login")
    Observable<LoginBean> login(@Query("moblie") String phone, @Query("password") String pwd);
    //修改密码
    @POST("api/users/forget")
    Observable<RegisterBean> forget(@Query("moblie") String phone, @Query("code") String mobilelz, @Query("password") String pwd, @Query("password2") String pwd2);
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
    @GET("api/Comment/comment")
    Observable<OtherCommentBean> getComment(@Query("topic_id")String topic_id,@Query("page")String page,@Query("limit")String limit);
    //赞
    @GET("api/Comment/Hits")
    Observable<ZanBean> getCommentZan(@Query("comment")String topic_id);
    //发表评论
    @FormUrlEncoded
    @POST("api/Comment/comment_add")
    Observable<SendCommentBean> getSendComment(@Field("user_id") String user_id,@Field("topic_id") String topic_id,@Field("content") String content);
    //报错
    @FormUrlEncoded
    @POST("api/Comment/mistake")
    Observable<MistakeBean> getMistake(@Field("user_id") String user_id, @Field("topic_id") String topic_id, @Field("content") String content);
    //收藏
    @GET("api/Share/Collection")
    Observable<CollectionBean> getCollection(@Query("user_id")String user_id, @Query("topic_id")String topic_id);
    //查看收藏
    @GET("api/Share/Collection_records")
    Observable<CollectionRecordsBean> getCollectionRecords(@Query("user_id")String user_id);
    //清空收藏
    @GET("api/Share/Collection_Dell")
    Observable<CollectionDellBean> getCollectionDell(@Query("user_id")String user_id);
}