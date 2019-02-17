package com.liujiakuo.core.http;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public class CommonRequest<T> {
    private Request request;
    private String url;//请求路径
    private IParseNetwork<T> mParseNetwork;
    private NetCallBack<T> mNetCallBack;

    public CommonRequest(String path) {
        this(path, null);
    }

    public CommonRequest(String host, String path, Object tag) {
        url = host + path;
        request = new Request.Builder()
                .url(host + path)//拼接host：path
                .tag(tag)
                .build();
    }

    public CommonRequest(String path, Object tag) {
        url = HttpClient.mBaseUrl + path;
        request = new Request.Builder()
                .url(HttpClient.mBaseUrl + path)//拼接host：path
                .tag(tag)
                .build();
    }

    public CommonRequest<T> setNetCallBack(@NonNull NetCallBack<T> callBack) {
        mNetCallBack = callBack;
        return this;
    }

    public CommonRequest<T> setParseNetwork(@NonNull IParseNetwork<T> parseNetwork) {
        mParseNetwork = parseNetwork;
        return this;
    }

    public NetCallBack<T> getNetClassBack() {
        return mNetCallBack;
    }

    public IParseNetwork<T> getParseNetwork() {
        return mParseNetwork;
    }

    public String getUrl() {
        return url;
    }

    public Request getRequest() {
        return request;
    }


    public interface NetCallBack<T> {
        void onFailure(Exception e);

        void onResponse(T response) throws IOException;
    }


}
