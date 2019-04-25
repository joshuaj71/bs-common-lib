package com.bestseller.commonlib.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
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
        setContentView(R.layout.bs_base_dialog_loading);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            // 设置宽度、高度、密度、对齐方式
//            float density = getDensity(context);
//            params.width = (int) (width * density);
//            params.height = (int) (height * density);
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.gravity = Gravity.CENTER_HORIZONTAL;
            window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.color_base_loading_dialog_bg)));
            window.setAttributes(params);
        }
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSysBars();
        }
    }

    private void hideSysBars() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}