package com.bestseller.commonlib.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

public abstract class BSBaseActivity extends AppCompatActivity {
    protected Activity activity = this;
    protected Context context;

    private Dialog mLoadingDialog;

    private AlertDialog.Builder builder;
    private AlertDialog tipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideStatusBar();

        context = this;
        mLoadingDialog = new BaseLoadingDialog(this);
        builder = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        initView(savedInstanceState);

        initListener();

        initData();

    }

    private void hideStatusBar() {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initListener();

    protected abstract void initData();

    public void closeLoadingDialog() {
        if (this.mLoadingDialog != null && mLoadingDialog.isShowing() && isValidContext(this)) {
            this.mLoadingDialog.dismiss();
        }
    }

    public Dialog showLoadingDialog() {
        if ((!isFinishing()) && (this.mLoadingDialog == null)) {
            this.mLoadingDialog = new BaseLoadingDialog(this);
        }
        if (isValidContext(this)) {
            this.mLoadingDialog.show();
        }
        return mLoadingDialog;
    }

    public void setNewBaseLoadingDialog(Dialog newBaseLoadingDialog){
        this.mLoadingDialog = newBaseLoadingDialog;
    }

    private boolean isValidContext(Context c) {
        Activity a = (Activity) c;
        if (a.isDestroyed() || a.isFinishing()) {
            Logger.d("Activity is invalid.  " + " isDestoryed-->"
                    + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
            return false;
        } else {
            return true;
        }
    }

    public void showTipDialog(String tip) {
        if (!TextUtils.isEmpty(tip)) {
            builder.setMessage(tip);
        } else {
            builder.setMessage("未知消息");
        }
        tipDialog = builder.create();
        tipDialog.show();
    }

}
