package com.botian.yihu.api;

import com.botian.yihu.util.MyApplication;
import com.botian.yihu.util.NetWorkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//缓存7天
public class Api7Days {

    public static String baseUrl = "http://btsc.botian120.com/";
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    public static ApiService apiService;
    //单例
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api7Days.class) {
                if (apiService == null) {
                    new Api7Days();
                }
            }
        }
        return apiService;
    }

    private Api7Days() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(MyApplication.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (!NetWorkUtils.isNetConnected(MyApplication.getContext())) {
                            int maxStale = 60 * 60 * 24 * 7; // 离线时缓存保存4周,单位:秒
                            CacheControl tempCacheControl = new CacheControl.Builder()
                                    .onlyIfCached()
                                    .maxStale(maxStale, TimeUnit.SECONDS)
                                    .build();
                            request = request.newBuilder()
                                    .cacheControl(tempCacheControl)
                                    .build();
                        }
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept( Chain chain) throws IOException {
                        Request request = chain.request();
                        Response originalResponse = chain.proceed(request);
                        int maxAge = 60 * 60 * 24 * 7;    // 在线缓存,单位:秒
                        return originalResponse.newBuilder()
                                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .removeHeader("Cache-Control")
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .build();
                    }
                })
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

}