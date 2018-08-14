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
import com.botian.yihu.beans.MyNoteSection;
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
import com.botian.yihu.beans.UploadPhoto;
import com.botian.yihu.beans.Version;
import com.botian.yihu.beans.VideoBuy;
import com.botian.yihu.beans.VideoCataLog;
import com.botian.yihu.beans.VideoCatalog2;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.beans.VideoComment;
import com.botian.yihu.beans.VideoInfo;
import com.botian.yihu.beans.VodeoBuyList;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.beans.ZanNum;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
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
    Observable<RegisterBean> getIdentify(@Query("mobile") String moblie);

    //注册
    @POST("api/Usres/sendcode")
    Observable<RegisterBean> register(@Query("username") String username, @Query("mobile") String moblie, @Query("password") String pwd, @Query("code") String code, @Query("mac") String mac, @Query("os") String os);

    //登陆
    @POST("api/Usres/login")
    Observable<LoginBean> login(@Query("moblie") String phone, @Query("password") String pwd, @Query("mac") String mac, @Query("os") String os);

    //修改密码
    @POST("api/Usres/forget")
    Observable<RegisterBean> forget(@Query("moblie") String phone, @Query("code") String mobilelz, @Query("password") String pwd, @Query("password2") String pwd2);

    //修改个人资料
    @POST("api/Usres/personal")
    Observable<ChangeUserInfo> changeUserInfo(@Query("id") String id, @Query("username") String username, @Query("sex") String sex);

    //发送版本信息
    @POST("api/version/version_updated")
    Observable<Version> getVersion(@Query("version") String version);

    //上传头像
    @Multipart
    @POST("api/usres/upload")
    Observable<UploadPhoto> getPhoto(@Query("id") String id, @Part MultipartBody.Part file);

    //get请求科目
    @GET("api/Column")
    Observable<SubjectBean> getSubject(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter9, @Query("token_api") String token);

    //得到章节列表
    @GET("api/chapter")
    Observable<ChapterPracticeListBean> getChapterPracticeList(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("filter[]") String filter8, @Query("filter[]") String filter9, @Query("token_api") String token);

    //得到章节题
    @GET("api/section")
    Observable<PracticeAnswer> getPracticeAnswer(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //得到章节材料标题
    @GET("api/material")
    Observable<Material> getPracticeMterial(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //得到章节材料题
    @GET("api/stuff")
    Observable<PracticeAnswer> getPracticeStuff(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //显示评论
    @GET("api/review")
    Observable<OtherCommentBean> getComment(@Query("filter[]") String filter1, @Query("addon") String usres, @Query("page") String page, @Query("limit") String limit, @Query("orderBy[]") String order, @Query("token_api") String token);

    //评论赞
    @POST("api/praise/save")
    Observable<ZanBean> getCommentZan(@Query("mid") String mid, @Query("userid") String userid, @Query("iscai") String iscai);

    //题赞
    @POST("api/laud/save")
    Observable<ZanBean> getTiZan(@Query("mid") String mid, @Query("userid") String userid, @Query("cl") String cl, @Query("iscai") String iscai);

    //题赞数量
    @GET("api/laud")
    Observable<ZanNum> getTiZanNum(@Query("filter[]") String filter, @Query("token_api") String token);

    //发表评论
    @POST("api/review/save")
    Observable<SendCommentBean> getSendComment(@Query("userid") String user_id, @Query("mid") String topic_id, @Query("content") String content, @Query("cl") String cl);

    //新增笔记
    @POST("api/bj/save")
    Observable<No> addNote(@Query("mid") String mid, @Query("userid") String userid, @Query("content") String content, @Query("cl") String cl);

    //笔记列表
    @POST("api/bj")
    Observable<MyNoteSection> NoteList(@Query("addon") String addon, @Query("filter[]") String filter);

    //意见反馈
    //@FormUrlEncoded
    @POST("api/Guestbook/save")
    Observable<MistakeBean> getMistake(@Query("uid") String user_id, @Query("content") String content);

    //收藏
    //@GET("api/sc/save")
    //Observable<CollectionBean> getCollection(@Query("mid")String mid, @Query("userid")String userid, @Query("cl")String cl);
    //查看收藏材料标题
    //@GET("api/sc")
    //Observable<CollectMaterialTitle> getCollectionMaterialTitle(@Query("addon")String material, @Query("filter[]")String filter1, @Query("filter[]")String filter2, @Query("page")String page, @Query("pageSize")String pageSize);
    //查看收藏
    //@GET("api/sc")
    //Observable<MyCollection> getCollectionRecords(@Query("addon")String addon,@Query("filter[]")String filter, @Query("filter[]")String filter2, @Query("page")String page, @Query("pageSize")String pageSize);
    //清空收藏
    //@GET("api/Share/Collection_Dell")
    //Observable<CollectionDellBean> getCollectionDell(@Query("user_id")String user_id);
    //视频分类
    @GET("api/video")
    Observable<VideoClass> getVideoClass(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("filter[]") String filter9, @Query("token_api") String token);

    //视频分类浏览次数
    @POST("api/videolike/save")
    Observable<No> getVideoClassNum(@Query("mid") String mid, @Query("userid") String userid);

    //视频目录
    @GET("api/directory")
    Observable<VideoCataLog> getVideoDirectory(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //视频目录第一条
    @GET("api/directory")
    Observable<VideoCatalog2> getVideoDirectory1(@Query("orderBy[]") String orderBy,@Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("page") String page, @Query("pageSize") String limit, @Query("filter[]") String filter9, @Query("token_api") String token);

    //视频数据
    @GET("api/videodata")
    Observable<VideoInfo> getVideoInfo(@Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //视频评论列表
    @GET("api/videolaud")
    Observable<VideoComment> getVideoCommentList(@Query("filter[]") String filter, @Query("addon") String usres, @Query("page") String page, @Query("pageSize") String limit, @Query("orderBy[]") String order, @Query("token_api") String token);

    //视频评论赞
    @GET("api/videodz/save")
    Observable<ZanBean> getVideoCommentZan(@Query("mid") String mid, @Query("userid") String userid, @Query("iscai") String iscai, @Query("token_api") String token);

    //发表视频评论
    @POST("api/videolaud/save")
    Observable<SendCommentBean> sendVideoComment(@Query("userid") String mid, @Query("mid") String userid, @Query("content") String content);

    //轮播图
    @GET("api/adlist")
    Observable<Adlist> getAdlist(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //资讯标签
    @GET("api/information")
    Observable<NewsLable> getNewsLable(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("filter[]") String filter9, @Query("token_api") String token);

    //资讯列表
    @GET("api/spy")
    Observable<NewsList> getNewsList(@Query("filter[]") String filter3, @Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("page") String page, @Query("pageSize") String limit, @Query("orderBy[]") String order, @Query("filter[]") String filter9, @Query("token_api") String token);

    //模拟考试场地
    @GET("api/cbt")
    Observable<MoniTest> getMoniTestPlace(@Query("orderBy[]") String orderBy,@Query("noPage") String page, @Query("filter[]") String filter, @Query("filter[]") String filter9, @Query("token_api") String token);

    //模拟考试题
    @GET("api/cbtsection")
    Observable<MoniTopic> getMoniTest(@Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("noPage") String page, @Query("filter[]") String filter9, @Query("token_api") String token);

    //模拟考试材料
    @GET("api/cbtmaterial")
    Observable<MoniCl> getMoniCl(@Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("noPage") String page, @Query("filter[]") String filter9, @Query("token_api") String token);

    //模拟考试材料题
    @GET("api/cbtstuff")
    Observable<MoniTopic> getMoniTestCl(@Query("filter[]") String filter, @Query("filter[]") String filter2, @Query("noPage") String page, @Query("filter[]") String filter9, @Query("token_api") String token);

    //模拟考试交卷
    @POST("api/cbtfraction/cbt_fraction")
    Observable<No> postMoni(@Query("userid") String userid, @Query("overtine") String overtine, @Query("fartion") String fartion, @Query("correct") String correct, @Query("wrong") String wrong, @Query("corrects") String corrects, @Query("wrongs") String wrongs);

    //资讯添加评论
    @POST("api/spycomment/save")
    Observable<SendNewsComment> sendNewComment(@Query("mid") String mid, @Query("userid") String userid, @Query("content") String content);

    //资讯评论列表
    @GET("api/spycomment/index")
    Observable<GetNewsComment> getNewComment(@Query("filter[]") String filter, @Query("addon") String addon, @Query("page") String page, @Query("pageSize") String limit, @Query("orderBy[]") String order, @Query("token_api") String token);

    //资讯浏览次数
    @POST("api/spylike/save")
    Observable<No> sendNewNum(@Query("mid") String mid, @Query("userid") String userid);

    //提交分享数据
    @POST("api/chaptershare/chapter_share")
    Observable<No> sendShareData(@Query("userid") String userid, @Query("chaprerid") String chaprerid, @Query("columnid") String columnid);

    //章节分享列表数据
    @GET("api/chaptershare")
    Observable<ShareList> getShareData(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("token_api") String token);

    //提交订单
    @POST("api/pay/paymoney")
    Observable<PayBeans> payMoney(@Query("userid") String userid, @Query("order") String order, @Query("account") String account, @Query("money") String money, @Query("payment") String payment);

    //我的订单
    @GET("api/pay")
    Observable<PayOrder> getPayOrder(@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("token_api") String token);

    //购买视频
    @POST("api/videopay/videobuy")
    Observable<VideoBuy> videoBuy(@Query("userid") String userid, @Query("column_id") String column_id, @Query("video_id") String video_id, @Query("directory_id") String directory_id, @Query("videodata_id") String videodata_id);

    //购买视频列表
    @GET("api/videopay")
    Observable<VodeoBuyList> getVideoBuyList(@Query("noPage") String noPage, @Query("filter[]") String filter1, @Query("filter[]") String filter2, @Query("filter[]") String filter3, @Query("token_api") String token);

    //购买模拟考试
    @POST("api/cbtpay/cbtbuy")
    Observable<MoniBuy> moniBuy(@Query("userid") String userid, @Query("column_id") String column_id, @Query("cbt_id") String cbt_id);

    //查询是否购买模拟考试
    @POST("api/cbtpay/cbtuser")
    Observable<SearchMoniBuy> searchMoniBuy(@Query("userid") String userid, @Query("column_id") String column_id, @Query("cbt_id") String cbt_id);

    //我的余额
    @GET("api/usres/usermoney")
    Observable<MyMoney> getMyMoney(@Query("id") String id, @Query("token_api") String token);

    //直播列表
    @GET("api/zhibo")
    Observable<Live> getLive(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("addon") String addon, @Query("filter[]") String filter3,@Query("filter[]") String filter4,@Query("token_api") String token);
    //高频考点
    @GET("api/high")
    Observable<HighTest> getHighTest( @Query("noPage") String noPage, @Query("filter[]") String filter,@Query("filter[]") String filter2,@Query("filter[]") String filter3,@Query("token_api") String token);
    //考前押题列表
    @GET("api/bet")
    Observable<KaoQianYaTiList> getKaoQianList(@Query("orderBy[]") String orderBy,@Query("noPage") String noPage, @Query("filter[]") String filter, @Query("filter[]") String filter2,  @Query("token_api") String token);
    //考前押题购买
    @POST("api/betpay/betbuy")
    Observable<KaoQianBuy> KaoQianBuy(@Query("userid") String userid, @Query("column_id") String column_id, @Query("bet_id") String bet_id);
    //查询考前押题是否购买
    @POST("api/betpay/userbuy")
    Observable<SearchKaoQianBuy> SearchKaoQianBuy(@Query("userid") String userid, @Query("column_id") String column_id, @Query("bet_id") String bet_id);
    //考前押题常规题
    @GET("api/betquestion")
    Observable<KaoQianNormal> getKaoQianNormal(@Query("noPage") String noPage, @Query("filter[]") String filter1, @Query("filter[]") String filter2,  @Query("filter[]") String filter3,@Query("token_api") String token);
    //考前押题材料标题
    @GET("api/betstuff")
    Observable<KaoQianClTitle> getKaoQianStuffTitle(@Query("noPage") String noPage, @Query("filter[]") String filter1, @Query("filter[]") String filter2, @Query("filter[]") String filter3, @Query("token_api") String token);
    //考前押题材料题
    @GET("api/betst")
    Observable<KaoQianNormal> getKaoQianStuff(@Query("noPage") String noPage, @Query("filter[]") String filter1, @Query("filter[]") String filter2, @Query("filter[]") String filter3, @Query("token_api") String token);
}
