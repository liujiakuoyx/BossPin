package com.liujiakuo.boss;

import android.support.multidex.MultiDexApplication;

import com.liujiakuo.core.task.CoreThreadPool;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化线程池
        CoreThreadPool.initThreadPool();
    }
}
