package com.bestseller.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import java.io.File;

/**
 * Author:Joshua
 * Date:2019/2/25
 * Description:
 */
public class UpdateUtil {
    public static final int REQUEST_CODE_APP_INSTALL = 0x99;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean hasInstallPermission(Context context){
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
    public static void startInstallPermissionSettingActivity(Activity activity) {
        if (activity == null){
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        activity.startActivityForResult(intent,REQUEST_CODE_APP_INSTALL);
    }

    //安装应用
    public static void installApk(Context context,File apk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
        } else {//Android7.0之后获取uri要用contentProvider
            Uri uri = FileUtils.getUriFromFile(context, apk);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
