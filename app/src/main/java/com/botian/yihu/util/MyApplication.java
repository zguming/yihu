package com.botian.yihu.util;

import android.app.Application;
import android.content.Context;


import com.mob.MobSDK;

import org.litepal.LitePal;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(this);
        MobSDK.init(this);
    }


    public static Context getContext() {
        return context;
    }

}