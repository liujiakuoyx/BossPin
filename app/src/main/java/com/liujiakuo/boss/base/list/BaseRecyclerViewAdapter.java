package com.liujiakuo.boss.base.list;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mData = new ArrayList<T>();

    public List<T> getData() {
        //使用unmodifiablelist，避免外界获取到data的引用对数据做更改
        return Collections.unmodifiableList(mData);
    }

    public T getItem(int position) {
        if (position < 0 || position >= mData.size()) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * 通知列表刷新
     *
     * @param items   新数据
     * @param refresh 是否是下拉刷新，下拉刷新清除原来的数据
     */
    public void updateDataNotifyItem(List<T> items, boolean refresh) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (refresh) {
            mData.clear();
        }
        int origCount = getBasicItemCount();
        mData.addAll(items);
        if (refresh) {
            notifyDataSetChanged();
        } else {
            if (items != null && !items.isEmpty()) {
                //上拉加载更多，添加尾部数据
                notifyItemRangeInserted(origCount, items.size());
            }

        }
    }

    public int getBasicItemCount() {
        return getData().size();
    }
}
