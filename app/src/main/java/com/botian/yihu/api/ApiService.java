package com.botian.yihu.api;

import com.botian.yihu.beans.Adlist;
import com.botian.yihu.beans.ChangeUserInfo;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.beans.CollectionBean;
import com.botian.yihu.beans.CollectionDellBean;
import com.botian.yihu.beans.LoginBean;
import com.botian.yihu.beans.Material;
import com.botian.yihu.beans.MistakeBean;
import com.botian.yihu.beans.MyCollection;
import com.botian.yihu.beans.MyNoteSection;
import com.botian.yihu.beans.NewsLable;
import com.botian.yihu.beans.NewsList;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.PracticeAnswer;
import com.botian.yihu.beans.PracticeBean;
import com.botian.yihu.beans.RegisterBean;
import com.botian.yihu.beans.SendCommentBean;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.beans.UploadPhoto;
import com.botian.yihu.beans.Version;
import com.botian.yihu.beans.VideoCataLog;
import com.botian.yihu.beans.VideoCatalog2;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.beans.VideoComment;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.beans.ZanNum;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public interface ApiService {
    //发送验证码
    @POST("api/Usres/sendsms")
    Observable<RegisterBean> getIdentify(@Query("mobile") String moblie,@Query("token_api")String token_api);
    //注册
    @POST("api/Usres/sendcode")
    Observable<RegisterBean> register(@Query("username") String username, @Query("mobile") String moblie, @Query("password") String pwd, @Query("version") String version, @Query("code") String code,@Query("token_api")String token_api);
    //登陆
    @POST("api/Usres/login")
    Observable<LoginBean> login(@Query("moblie") String phone, @Query("password") String pwd,@Query("token_api")String token_api);
    //修改密码
    @POST("api/Usres/forget")
    Observable<RegisterBean> forget(@Query("moblie") String phone, @Query("code") String mobilelz, @Query("password") String pwd, @Query("password2") String pwd2,@Query("token_api")String token_api);
    //修改个人资料
    @POST("api/Usres/personal")
    Observable<ChangeUserInfo> changeUserInfo(@Query("id") String id, @Query("username") String username, @Query("sex") String sex,@Query("token_api")String token_api);
    //发送版本信息
    @GET("api/version/version_updated")
    Observable<Version> getVersion();
    //上传头像
    @Multipart
    @POST("api/usres/upload")
    Observable<UploadPhoto> getPhoto(@Query("id") String id, @Part  MultipartBody.Part file,@Query("token_api")String token_api);
    //get请求科目
    @GET("api/Column")
    Observable<SubjectBean> getSubject(@Query("noPage") String noPage,@Query("token_api")String token_api);
    //得到章节列表
    @GET("api/chapter")
    Observable<ChapterPracticeListBean> getChapterPracticeList(@Query("noPage") String noPage, @Query("mid")String mid,@Query("token_api")String token_api);
    //得到章节题
    @GET("api/section")
    Observable<PracticeAnswer> getPracticeAnswer(@Query("noPage") String noPage, @Query("typeid")String typeid,@Query("token_api")String token_api);
    //得到章节材料标题
    @GET("api/material")
    Observable<Material> getPracticeMterial(@Query("noPage") String noPage, @Query("typeid")String typeid,@Query("token_api")String token_api);
    //得到章节材料题
    @GET("api/stuff")
    Observable<PracticeAnswer> getPracticeStuff(@Query("noPage") String noPage, @Query("typeid")String typeid,@Query("token_api")String token_api);
    //显示评论
    @GET("api/review")
    Observable<OtherCommentBean> getComment(@Query("filter[]")String filter1,@Query("addon")String usres,@Query("page")String page,@Query("limit")String limit,@Query("token_api")String token_api);
    //评论赞
    @POST("api/praise/save")
    Observable<ZanBean> getCommentZan(@Query("mid")String mid,@Query("userid")String userid,@Query("iscai")String iscai,@Query("token_api")String token_api);
    //题赞
    @POST("api/laud/save")
    Observable<ZanBean> getTiZan(@Query("mid")String mid,@Query("userid")String userid,@Query("cl")String cl,@Query("iscai")String iscai,@Query("token_api")String token_api);
    //题赞数量
    @GET("api/laud")
    Observable<ZanNum> getTiZanNum(@Query("filter[]")String filter, @Query("token_api")String token_api);
    //发表评论
    @POST("api/review/save")
    Observable<SendCommentBean> getSendComment(@Query("userid") String user_id,@Query("mid") String topic_id,@Query("content") String content,@Query("cl") String cl,@Query("token_api")String token_api);
    //新增笔记
    @POST("api/bj/save")
    Observable<No> addNote(@Query("mid")String mid, @Query("userid")String userid, @Query("content")String content, @Query("cl")String cl,@Query("token_api")String token_api);
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
    //视频分类
    @GET("api/video")
    Observable<VideoClass> getVideoClass(@Query("noPage")String noPage,@Query("filter[]")String filter);
    //视频分类浏览次数
    @GET("api/videolike/save")
    Observable<No> getVideoClassNum(@Query("mid")String mid,@Query("userid")String userid);
    //视频目录
    @GET("api/directory")
    Observable<VideoCataLog> getVideoDirectory(@Query("noPage")String noPage,@Query("filter[]")String filter);
    //视频目录第一条
    @GET("api/directory")
    Observable<VideoCatalog2> getVideoDirectory1(@Query("filter[]")String filter, @Query("filter[]")String filter2, @Query("page")String page, @Query("pageSize")String limit);
    //视频数据
    @GET("api/videodata")
    Observable<VideoInfo> getVideoInfo( @Query("filter[]")String filter);
    //视频评论列表
    @GET("api/videolaud")
    Observable<VideoComment> getVideoCommentList(@Query("filter[]")String filter, @Query("addon")String  usres,@Query("page")String page,@Query("pageSize")String limit);
    //视频评论赞
    @GET("api/videodz/save")
    Observable<ZanBean> getVideoCommentZan(@Query("mid")String mid,@Query("userid")String userid,@Query("iscai")String iscai);
    //发表视频评论
    @GET("api/videolaud/save")
    Observable<SendCommentBean> sendVideoComment(@Query("mid")String mid,@Query("userid")String userid,@Query("content")String content);
    //轮播图
    @GET("api/adlist")
    Observable<Adlist> getAdlist(@Query("noPage")String noPage,@Query("filter[]")String filter,@Query("token_api")String token_api);
    //资讯标签
    @GET("api/information")
    Observable<NewsLable> getNewsLable( @Query("noPage")String noPage,@Query("filter[]")String filter,@Query("token_api")String token_api);
    //资讯列表
    @GET("api/spy")
    Observable<NewsList> getNewsList(@Query("filter[]")String filter,@Query("page")String page,@Query("pageSize")String limit,@Query("token_api")String token_api);
}