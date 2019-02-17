package com.liujiakuo.core.http;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public class HttpClient {
    private static OkHttpClient client;
    private static Context mApplicationContext;
    private static final Handler MH = new Handler(Looper.getMainLooper());

    /**
     * 需要在Application初始化
     */
    public static void initOkHttp(Application application) {
        mApplicationContext = application;
        if (client == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
    }

    public static OkHttpClient getClient() {
        return client;
    }

    /**
     * 添加一个网络请求
     *
     * @param request
     */
    public static boolean addRequest(final CommonRequest request) {
        //防止引用
        final WeakReference<CommonRequest> reference = new WeakReference<>(request);
        if (client == null || request == null) {
            return false;
        }
        if (!checkNetwork(mApplicationContext)) {
            //网络异常
            Toast.makeText(mApplicationContext, "网络没有连接", Toast.LENGTH_SHORT).show();
            return false;
        }
        client.newCall(request.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                CommonRequest commonRequest = reference.get();
                if (commonRequest == null) {
                    return;
                }
                final CommonRequest.NetCallBack netClassBack = commonRequest.getNetClassBack();
                if (netClassBack != null) {
                    //调度到主线程
                    MH.post(new Runnable() {
                        @Override
                        public void run() {
                            netClassBack.onFailure(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                CommonRequest commonRequest = reference.get();
                if (commonRequest == null) {
                    return;
                }
                final IParseNetwork parseNetwork = commonRequest.getParseNetwork();
                final CommonRequest.NetCallBack netClassBack = commonRequest.getNetClassBack();
                if (netClassBack != null) {
                    //调到主线程
                    MH.post(new Runnable() {
                        @Override
                        public void run() {
                            if (parseNetwork == null) {
                                netClassBack.onResponse(null);
                            } else {
                                //得到数据
                                try {
                                    netClassBack.onResponse(parseNetwork.parseNetworkResponse(response.body().string()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    netClassBack.onFailure(e);
                                }
                            }
                        }
                    });
                }
            }
        });
        return true;
    }

    /**
     * 检查网络连接
     */
    public static boolean checkNetwork(Context context) {
        boolean flag = false;
        try {
            ConnectivityManager cwjManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cwjManager.getActiveNetworkInfo() != null)
                flag = cwjManager.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * onDestroy的时候停止网络请求
     */
    public static void cancelByTag(Object tag) {
        if (client == null) {
            return;
        }
        List<Call> calls = client.dispatcher().queuedCalls();
        if (calls != null && !calls.isEmpty()) {
            for (Call call : calls) {
                if (call.request().tag().equals(tag)) {
                    call.cancel();
                }
            }
        }
    }
}
