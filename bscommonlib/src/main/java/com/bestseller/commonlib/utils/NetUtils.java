package com.bestseller.commonlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Author:Joshua
 * Date:2019/2/18
 * Description:
 */
public class NetUtils {
    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean IsNetWorkEnable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        }
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 判断当前网络是否已经连接
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否连接Wifi
     *
     * @param context Context
     */
    public static boolean isWifiAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() && networkInfo
                .getType() == ConnectivityManager.TYPE_WIFI);
    }
}
