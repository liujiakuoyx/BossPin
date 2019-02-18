package com.liujiakuo.core.http;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by 佳阔 on 2019/2/17.
 * 负责管理数据解析和回调
 */

public class CommonRequest<T> {
    private Request request;
    private IParseNetwork<T> mParseNetwork;
    private NetCallBack<T> mNetCallBack;

    public CommonRequest(Request request, IParseNetwork<T> parseNetwork) {
        this.request = request;
        this.mParseNetwork = parseNetwork;
    }

    public CommonRequest<T> setNetCallBack(@NonNull NetCallBack<T> callBack) {
        mNetCallBack = callBack;
        return this;
    }

    public CommonRequest<T> setParseNetwork(@NonNull IParseNetwork<T> parseNetwork) {
        mParseNetwork = parseNetwork;
        return this;
    }

    public NetCallBack<T> getNetCallBack() {
        return mNetCallBack;
    }

    public IParseNetwork<T> getParseNetwork() {
        return mParseNetwork;
    }

    public Request getRequest() {
        return request;
    }


    public interface NetCallBack<T> {
        void onFailure(Exception e);

        void onResponse(T response);
    }


}
