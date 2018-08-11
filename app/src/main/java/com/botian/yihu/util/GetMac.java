package com.botian.yihu.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.NetworkInterface;

public class GetMac {


    private static String mac;


    public static String getMac(Context context) {


        if (mac == null) {


            try {


                NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");


                byte[] addrByte = networkInterface.getHardwareAddress();


                StringBuilder sb = new StringBuilder();


                for (byte b : addrByte) {


                    sb.append(String.format("%02X:", b));


                }


                if (sb.length() > 0) {


                    sb.deleteCharAt(sb.length() - 1);


                }


                mac = sb.toString();


            } catch (Exception e) {


                e.printStackTrace();


                WifiManager wifiM = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);


                try {


                    WifiInfo wifiI = wifiM.getConnectionInfo();


                    mac = wifiI.getMacAddress();


                } catch (NullPointerException e1) {


                    e1.printStackTrace();


                    mac = "02:00:00:00:00:00";


                }


            }


        }


        return mac;


    }
}
