package com.bestseller.commonlib.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bestseller.commonlib.utils.LogUtils.LogUtil;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.LinkedList;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Author:Joshua
 * Date:2019/2/22
 * Description:
 */
public class BSBaseApplication extends Application {
    private static BSBaseApplication myApp;
    private Context context;
    private LinkedList<Activity> mActivities = new LinkedList<>();

    static final String PURGE_ENABLED_KEY = "rx2.purge-enabled";
    static final String PURGE_PERIOD_SECONDS_KEY = "rx2.purge-period-seconds";


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        LitePalApplication.initialize(this);
        LitePal.initialize(getApplicationContext());

        Connector.getDatabase();
        initRxPurgeProperties();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtil.d(throwable);
            }
        });

    }

    /**
     * 初始化RxJava回收执行的周期
     * @see io.reactivex.internal.schedulers.SchedulerPoolFactory
     */
    private static void initRxPurgeProperties() {
        System.setProperty(PURGE_ENABLED_KEY, "true");
        System.setProperty(PURGE_PERIOD_SECONDS_KEY, "3600");
    }

    /**
     * Activity开启时添加Activity到集合
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivities.addFirst(activity);
    }

    /**
     * Activity退出时清除集合中的Activity.
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivities.remove(activity);
        }
    }

    public void exit() {
        for (Activity activity : mActivities) {
            if (mActivities != null) {
                activity.finish();
            }
        }
    }
}
