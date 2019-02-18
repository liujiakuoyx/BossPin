package com.liujiakuo.boss.base.list;

import android.util.Log;
import android.view.ViewGroup;

/**
 * liujiakuo on 2019/2/18 11:54
 * T 列表数据类型
 * HD 头部数据
 */
public abstract class BasePageListAdapter<T, HD> extends HeadFooterRecyclerAdapter<T, HD, Integer> {
    private final String TAG = "BasePageListAdapter";

    @Override
    protected BaseViewHolder<HD> createHeadViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseViewHolder<Integer> createFooterViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "createFooterViewHolder");
        return new CommonFooterHolder(parent);
    }

    public void setFooterLoadingState() {
        setFooterData(BaseFooterHolder.TYPE_LOADING);
    }

    public void setFooterRetryState() {
        setFooterData(BaseFooterHolder.TYPE_RETRY);
    }

    public void setFooterNoMoreState() {
        setFooterData(BaseFooterHolder.TYPE_SHOW_NO_MORE_VIEW);
    }

    public void setNoFooter() {
        setFooterData(null);
    }

}
