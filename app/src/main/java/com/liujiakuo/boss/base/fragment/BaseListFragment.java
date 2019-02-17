package com.liujiakuo.boss.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.base.list.HeadFooterRecyclerAdapter;
import com.liujiakuo.boss.utils.TextUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.http.IParseNetwork;

import java.io.IOException;
import java.util.List;

/**
 * Created by 佳阔 on 2019/2/17.
 * T 列表基本数据类型
 * D 网络返回的数据类型
 * HD 头部数据类型
 */

public abstract class BaseListFragment<T, D, HD> extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, LoadManager.ILoadLocalAction<D>, LoadManager.ILoadNetAction<D> {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private HeadFooterRecyclerAdapter<T, HD, Integer> mAdapter;
    private LoadManager<D> mLoadManager;


    @Override

    protected int getContentView() {
        return R.layout.base_recyclerview_pull_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        initAdapter();
        mRefreshLayout.setOnRefreshListener(this);
        mLoadManager = new LoadManager<>(this, this);
    }

    private void initAdapter() {
        mAdapter = createAdapter();
        if (mAdapter != null) {
            //初始化Recycler
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    protected abstract HeadFooterRecyclerAdapter<T, HD, Integer> createAdapter();

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        //加载数据
        mLoadManager.loadNetData(true);
    }

    /**
     * 构造网络请求的request
     */

    protected abstract void onNetResponse(D response, boolean refresh);

    public void updateAdapter(List<T> data, boolean refresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭没有完成的网络请求，防止内存溢出
        HttpClient.cancelByTag(this);
    }

    /**
     * 加载缓存数据,需要子类实现
     */
    @Override
    public D loadLocal() {
        return null;
    }

    @Override
    public void onLocalResponse(D response) {
        updateAdapterData(mAdapter, response, false, false);
    }

    @Override
    public abstract CommonRequest<D> createRequest(boolean isRefresh);

    /**
     * 网络数据返回
     */
    @Override
    public void onNetResponse(boolean isRefresh, D response) {
        mRefreshLayout.setRefreshing(false);
        updateAdapterData(mAdapter, response, isRefresh, true);
    }

    abstract protected void updateAdapterData(HeadFooterRecyclerAdapter<T, HD, Integer> adapter, D response, boolean isRefresh, boolean isNetResponse);

    /**
     * 加载失败
     */
    @Override
    public void onError(boolean isRefresh, Exception error) {
        mRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "加载异常", Toast.LENGTH_SHORT).show();
    }
}
