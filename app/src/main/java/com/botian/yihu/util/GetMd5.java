package com.botian.yihu.util;

import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class GetMd5 {

    public static String md5() {
        //long timeStampSec = System.currentTimeMillis()/1000;
        //String timestamp = String.format("%010d", timeStampSec)+"botian";

        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new    java.util.Date());
        String timestamp = date+"botian";
        //Log.d("TAG", "md5: "+timestamp);
        //String timestamp = "111botian";
        if (TextUtils.isEmpty(timestamp)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(timestamp.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
