package com.bestseller.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

/**
 * Author:Joshua
 * Date:2019/2/25
 * Description:
 */
public class PermissionUtil {
    public static final int REQUEST_CODE_APP_INSTALL = 0x99;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean hasInstallPermission(Context context){
        if (context == null){
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    /**
     * 开启设置安装未知来源应用权限界面
     * @param activity
     */
    @RequiresApi (api = Build.VERSION_CODES.O)
    public void startInstallPermissionSettingActivity(Activity activity) {
        if (activity == null){
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        activity.startActivityForResult(intent,REQUEST_CODE_APP_INSTALL);
    }

}
