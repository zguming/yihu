package com.botian.yihu.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class GetVersionName {
    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.botian.yihu", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //Log.e("msg",e.getMessage());
        }
        return verName;
    }
}
