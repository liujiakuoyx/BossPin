package com.liujiakuo.core.http;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.io.IOException;
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
    public static String mBaseUrl;

    /**
     * 需要在Application初始化
     *
     * @param baseUrl host
     */
    public static void initOkHttp(Application application, String baseUrl) {
        mApplicationContext = application;
        mBaseUrl = baseUrl;
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

    public static void addRequest(final CommonRequest request) {
        if (client == null || request == null) {
            return;
        }
        if (!checkNetwork(mApplicationContext)) {
            //网络异常
            Toast.makeText(mApplicationContext, "网络没有连接", Toast.LENGTH_SHORT).show();
            return;
        }
        client.newCall(request.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                CommonRequest.NetCallBack netClassBack = request.getNetClassBack();
                if (netClassBack != null) {
                    netClassBack.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                IParseNetwork parseNetwork = request.getParseNetwork();
                CommonRequest.NetCallBack netClassBack = request.getNetClassBack();
                if (netClassBack != null) {
                    if (parseNetwork == null) {
                        netClassBack.onResponse(null);
                    } else {
                        //得到数据
                        netClassBack.onResponse(parseNetwork.parseNetworkResponse(response.body().string()));
                    }
                }
            }
        });
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
