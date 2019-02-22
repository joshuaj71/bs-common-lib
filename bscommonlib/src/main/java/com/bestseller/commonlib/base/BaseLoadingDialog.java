package com.bestseller.commonlib.base;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.bestseller.commonlib.R;


/**
 * Created by joshua on 2017/4/11.
 */


public class BaseLoadingDialog extends Dialog {
    private static int default_width = 380; // 默认宽度
    private static int default_height = 340;// 默认高度
    private ProgressBar loadingBar;

    public BaseLoadingDialog(Context context) {
        this(context, default_width, default_height);
    }

    public BaseLoadingDialog(Context context, int width, int height) {
        super(context, R.style.Theme_bs_base_loading_dialog);
        setContentView(R.layout.bs_base_dialog_loading);
        setCancelable(true);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            // 设置宽度、高度、密度、对齐方式
//            float density = getDensity(context);
//            params.width = (int) (width * density);
//            params.height = (int) (height * density);
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.gravity = Gravity.CENTER;
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.color_base_loading_dialog_bg)));
            window.setAttributes(params);
            loadingBar = findViewById(R.id.bsBaseProgressBar000);
            loadingBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor
                    (R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /**
     * 获取显示密度
     *
     * @param context
     * @return
     */
    public float getDensity(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.density;
    }
}