package com.liujiakuo.boss;

import android.support.multidex.MultiDexApplication;

import com.liujiakuo.boss.utils.SPConfig;
import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.task.CoreThreadPool;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化线程池
        CoreThreadPool.initThreadPool();
        //初始化okHttp
        HttpClient.initOkHttp(this);
        //初始化sp
        SPConfig.init(this);
    }
}
