package com.bestseller.commonlib.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * @author:Joshua
 * @data:2019/2/18
 * @des:
 */
public class FileUtils {


    public static Uri getUriFromFile(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider",
                file);
    }
}
