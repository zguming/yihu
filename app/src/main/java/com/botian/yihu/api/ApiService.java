package com.botian.yihu.api;

import com.botian.yihu.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public interface ApiService {
    //接口地址
    //String url = "https://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0";

    //get请求
    //@GET("v1/daily/comic_lists/0?since=0&gender=0")
    //Observable<mybean> getMyData();  //泛型里的为Bean类
    //http://btsc.botian120.com/admin/sms/send_code
    //get请求
    //@GET("v1/daily/comic_lists/0?since=0&gender=0")
    //Observable<mybean> getMyData();  //泛型里的为Bean类
    //发送验证码
    //http://btsc.botian120.com/api/sms/sendsms
    //字段 手机号 mobile
    //相应 code 0 成功  1失败
    //Msg 内容
    @FormUrlEncoded
    @POST("sendsms")
    Observable<RegisterBean> getIdentify(@Field("tel") String phone);
    //注册
    //http://btsc.botian120.com/api/sms/send_code
    //字段 手机号 mobile  验证码 code  密码password
    //相应 code 0 成功  1失败
    //Msg 内容
    @FormUrlEncoded
    @POST("send_code")
    Observable<RegisterBean> register(@Field("mobile") String phone, @Field("mobilelz") String mobilelz, @Field("password") String pwd);
    //登陆
    //http://btsc.botian120.com/api/sms/login
    //字段 手机号 mobile   密码password
    //相应 code 0 成功  1失败
    //Msg  内容
    @FormUrlEncoded
    @POST("login")
    Observable<RegisterBean> login(@Field("mobile") String phone, @Field("password") String pwd);
    //修改密码
    //http://btsc.botian120.com/api/sms/forget
    //字段 手机号 mobile  验证码 code   密码1password  密码2 password2
    //相应 code 0 成功  1失败
    //Msg  内容
    @FormUrlEncoded
    @POST("forget")
    Observable<RegisterBean> forget(@Field("mobile") String phone, @Field("mobilelz") String mobilelz, @Field("password") String pwd, @Field("password2") String pwd2);
}