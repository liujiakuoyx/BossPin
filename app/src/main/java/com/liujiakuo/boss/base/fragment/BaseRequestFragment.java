package com.liujiakuo.boss.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.base.activity.BaseActivity;
import com.liujiakuo.boss.base.http.response.DataResponse;

/**
 * Created by 佳阔 on 2019/3/9.
 */

public abstract class BaseRequestFragment<D extends DataResponse> extends BaseFragment
        implements LoadManager.ILoadNetAction<D>, LoadManager.ILoadLocalAction<D> {
    private LoadManager<D> mLoadManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadManager = new LoadManager<>(this, this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadNetData();
    }

    private boolean loadNetData() {
        BaseActivity baseActivity = getBaseActivity();
        //loading dialog
        showDialog(true);
        //加载数据
        return mLoadManager.loadNetData(true);
    }


    @Override
    public void onNetResponse(boolean isRefresh, D response) {
        BaseActivity baseActivity = getBaseActivity();
        showDialog(false);
        onResponse(response);
    }

    protected abstract void onResponse(D response);

    @Override
    public void onError(boolean isRefresh, Exception error) {
        showDialog(false);
    }

    @Override
    public D loadLocal() {
        return null;
    }

    @Override
    public void onLocalResponse(D response) {

    }

    private void showDialog(boolean show) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            if (show) {
                baseActivity.showProgressDialog();
            } else {
                baseActivity.dismissProgressDialog();
            }
        }
    }
}
