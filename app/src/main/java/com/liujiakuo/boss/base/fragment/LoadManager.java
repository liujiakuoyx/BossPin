package com.liujiakuo.boss.base.fragment;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.liujiakuo.boss.utils.JsonUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.http.IParseNetwork;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public class LoadManager<T> {
    private ILoadNetAction<T> mLoadNetAction;
    private ILoadLocalAction<T> mLoadLocalAction;

    public LoadManager(ILoadNetAction<T> mLoadNetAction, ILoadLocalAction<T> mLoadLocalAction) {
        this.mLoadNetAction = mLoadNetAction;
        this.mLoadLocalAction = mLoadLocalAction;
    }

    public boolean loadNetData(final boolean isRefresh) {
        CommonRequest<T> request = mLoadNetAction.createRequest(isRefresh);
        if (request == null) {
            return false;
        }
        request.setNetCallBack(new CommonRequest.NetCallBack<T>() {
            @Override
            public void onFailure(Exception e) {
                mLoadNetAction.onError(isRefresh, e);
            }

            @Override
            public void onResponse(T response) {
                mLoadNetAction.onNetResponse(isRefresh, response);
            }
        });
        return HttpClient.addRequest(request);
    }

    /**
     * 加载本地
     */
    public void loadLocalData() {
        T data = mLoadLocalAction.loadLocal();
        mLoadLocalAction.onLocalResponse(data);
    }

    public interface ILoadNetAction<T> {

        CommonRequest<T> createRequest(boolean isRefresh);

        void onNetResponse(boolean isRefresh, T response);

        void onError(boolean isRefresh, Exception error);
    }

    public interface ILoadLocalAction<T> {
        T loadLocal();

        void onLocalResponse(T response);
    }
}
