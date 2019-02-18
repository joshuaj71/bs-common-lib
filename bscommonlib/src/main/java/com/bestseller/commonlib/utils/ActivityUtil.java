package com.bestseller.commonlib.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * @author:Joshua
 * @data:2019/2/18
 * @des:
 */
public class ActivityUtil {
    /**
     * 判断程序是否处于后台
     *
     * @param context 上下文
     * @return true表示程序当前处于后台，false表示程序当前处于前台
     */
    public static boolean isBackground(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
