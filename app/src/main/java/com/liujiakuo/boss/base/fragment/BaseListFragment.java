package com.liujiakuo.boss.base.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.base.http.response.PageDataResponse;
import com.liujiakuo.boss.base.list.BaseFooterHolder;
import com.liujiakuo.boss.base.list.BasePageListAdapter;
import com.liujiakuo.boss.base.list.BaseViewHolder;
import com.liujiakuo.boss.base.list.CommonItemDecoration;
import com.liujiakuo.boss.base.list.HeadFooterRecyclerAdapter;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.HttpClient;


/**
 * Created by 佳阔 on 2019/2/17.
 * T 列表基本数据类型
 * D 网络返回的数据类型
 * HD 头部数据类型
 */

public abstract class BaseListFragment<T, D extends PageDataResponse, HD> extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, LoadManager.ILoadLocalAction<D>, LoadManager.ILoadNetAction<D>,
        HeadFooterRecyclerAdapter.OnItemBindListener<HD, T, Integer> {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private BasePageListAdapter<T, HD> mAdapter;
    private LoadManager<D> mLoadManager;
    private boolean mRefreshing, mLoadMore;


    @Override

    protected int getContentView() {
        return R.layout.base_recyclerview_pull_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.progress_color_1),
                getContext().getResources().getColor(R.color.progress_color_2),
                getContext().getResources().getColor(R.color.progress_color_3));
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        //item改变不重新计算大小
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //分割线
        mRecyclerView.addItemDecoration(CommonItemDecoration.createVertical(getContext(), Color.parseColor("#f2f2f2"), 9));
        //去掉边缘的光晕效果
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mLoadManager = new LoadManager<>(this, this);
        initAdapter();
        //首次进入加载数据
        loadFirstData();
    }

    private void loadFirstData() {
        if (shouldLoadDataForFirstTime()) {
            mRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    //加载数据
                    mRefreshLayout.setRefreshing(true);
                    //加载数据
                    loadNetData(true);
                }
            });
        }
    }

    private void initAdapter() {
        mAdapter = createAdapter();
        if (mAdapter != null) {
            //初始化Recycler
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemBindListener(this);
            mAdapter.notifyDataSetChanged();
        }
    }

    public BasePageListAdapter getAdapter() {
        return mAdapter;
    }

    protected abstract BasePageListAdapter<T, HD> createAdapter();

    @Override
    public void onRefresh() {
        loadNetData(true);
    }

    private boolean loadNetData(boolean isRefresh) {
        if (mRefreshing && isRefresh || mLoadMore && !isRefresh) {
            return false;
        }

        if (isRefresh) {
            mRefreshing = true;
        } else {
            mLoadMore = true;
        }

        //加载数据
        return mLoadManager.loadNetData(isRefresh);
    }

    private void resetLoadingFlag(boolean isRefresh) {
        if (isRefresh) {
            mRefreshing = false;
        } else {
            mLoadMore = false;
        }
    }

    protected boolean shouldLoadDataForFirstTime() {
        return true;
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
        onResponse(response, false, false);
    }

    @Override
    public abstract CommonRequest<D> createRequest(boolean isRefresh);

    /**
     * 网络数据返回
     */
    @Override
    public void onNetResponse(boolean isRefresh, D response) {
        onResponse(response, isRefresh, true);
    }

    /**
     * 数据返回
     * isLoadNet 是否是网络返回
     */
    private void onResponse(D response, boolean isRefresh, boolean isLoadNet) {
        if (isRefresh) {
            mRefreshLayout.setRefreshing(false);
        }
        if (mAdapter != null) {
            updateAdapterData(mAdapter, response, isRefresh, isLoadNet);
            updateFooterState(response);
        }
        if (isLoadNet) {
            resetLoadingFlag(isRefresh);
        }

    }

    /**
     * 是否支持加载更多
     */
    protected boolean supportLoadMore() {
        return true;
    }

    //返回值代表加载更多是否已经加载完  false表示没有更多了
    abstract protected boolean checkHasMore(D response);

    /**
     * 更新footer状态
     *
     * @param response
     */
    protected void updateFooterState(D response) {
        if (mAdapter == null) {
            return;
        }
        if (!supportLoadMore() || mAdapter.isEmpty()) {
            mAdapter.setNoFooter();
        } else if (checkHasMore(response)) {
            mAdapter.setFooterLoadingState();
        } else {
            mAdapter.setFooterNoMoreState();
        }
    }

    abstract protected void updateAdapterData(BasePageListAdapter<T, HD> adapter, D response, boolean isRefresh, boolean isNetResponse);

    /**
     * 加载失败
     */
    @Override
    public void onError(boolean isRefresh, Exception error) {
        mRefreshLayout.setRefreshing(false);

        resetLoadingFlag(isRefresh);

        //更新footer为加载异常
        if (!isRefresh && mAdapter != null) {
            mAdapter.setFooterRetryState();
        }
        Toast.makeText(getContext(), "加载异常", Toast.LENGTH_SHORT).show();
    }

    private void checkLoadMore() {
        if (mAdapter.getFooterData() != null && mAdapter.getFooterData() == BaseFooterHolder.TYPE_LOADING) {
            Log.d(TAG, "checkLoadMore");
            //加载更多数据
            loadNetData(false);
        }
    }

    @Override
    public void bindHead(BaseViewHolder<HD> holder) {

    }

    @Override
    public void bindBasicItem(BaseViewHolder<T> holder, int position) {

    }

    @Override
    public void bindFooter(BaseViewHolder<Integer> holder) {
        Log.d(TAG, "bindFooter: " + holder.hashCode());
        if (mRecyclerView != null) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    checkLoadMore();//尝试加载更多
                }
            });
        }
    }
}
