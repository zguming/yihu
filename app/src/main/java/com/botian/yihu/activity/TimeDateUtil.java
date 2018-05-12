package com.botian.yihu.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
//时间戳转换
public class TimeDateUtil {
    
    public static String getDateToString(String time, String pattern) {
        SimpleDateFormat sdr = new SimpleDateFormat(pattern);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }
}