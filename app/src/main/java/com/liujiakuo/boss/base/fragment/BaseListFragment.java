package com.liujiakuo.boss.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;


    @Override
    protected int getContentView() {
        return R.layout.base_recyclerview_pull_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        //加载数据
        loadNetData();
    }

    /**
     * 加载网络数据
     */
    private void loadNetData() {

    }
}
