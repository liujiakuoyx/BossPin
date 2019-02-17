package com.liujiakuo.boss;

import android.support.multidex.MultiDexApplication;

import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.task.CoreThreadPool;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class MyApplication extends MultiDexApplication {
    private final String BASE_URL = "http://localhost:8080";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化线程池
        CoreThreadPool.initThreadPool();
        //初始化okHttp
        HttpClient.initOkHttp(this, BASE_URL);
    }
}
