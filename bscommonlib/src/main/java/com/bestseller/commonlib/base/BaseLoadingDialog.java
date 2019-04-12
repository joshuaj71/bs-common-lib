package com.bestseller.commonlib.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bestseller.commonlib.R;


/**
 * Created by joshua on 2017/4/11.
 */


public class BaseLoadingDialog extends Dialog {
    private ProgressBar loadingBar;
    private TextView progressMsg000;
    private String defaultTip = "加载中...";


    public BaseLoadingDialog(Context context) {
        super(context, R.style.Theme_bs_base_loading_dialog);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            // 设置宽度、高度、密度、对齐方式
//            float density = getDensity(context);
//            params.width = (int) (width * density);
//            params.height = (int) (height * density);
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.color_base_loading_dialog_bg)));
            window.setAttributes(params);
        }
        setContentView(R.layout.bs_base_dialog_loading);
        progressMsg000 = findViewById(R.id.progressMsg000);
        loadingBar = findViewById(R.id.bsBaseProgressBar000);
        loadingBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor
                (R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        setCancelable(true);
    }
    public void setTip(String tip) {
        if (progressMsg000 != null) {
            progressMsg000.setText(tip);
        }
    }
    public void setDefaultTip() {
        if (progressMsg000 != null) {
            progressMsg000.setText(defaultTip);
        }
    }
}