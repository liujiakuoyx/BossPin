package com.liujiakuo.boss.base.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liujiakuo.boss.R;

/**
 * Created by 佳阔 on 2019/2/17.
 */

public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {
    private D mItemData;
    private int mBasicPos;

    public BaseViewHolder(int viewLayout, ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(viewLayout, parent, false));
    }

    //绑定数据
    public final void bindView(D itemData, int basicPos) {
        mBasicPos = basicPos;
        mItemData = itemData;
        bindView(itemData);
    }

    public void bindView(D itemData) {
        mItemData = itemData;
    }

    public <T extends View> T getView(int id) {
        T view = itemView.findViewById(id);
        return view;
    }

    public D getItemData() {
        return mItemData;
    }

    public Context getContext() {
        if (itemView != null) {
            return itemView.getContext();
        }
        return null;
    }

    //拿到减去头部之后的位置
    public int getBasicPos() {
        return mBasicPos;
    }

}
