package com.liujiakuo.boss.base.list;

import android.view.ViewGroup;

/**
 * liujiakuo on 2019/2/18 13:03
 */
public abstract class BaseFooterHolder extends BaseViewHolder<Integer> {
    public static final int TYPE_LOADING = 0; // 加载中
    public static final int TYPE_RETRY = 1; // 失败重试
    public static final int TYPE_SHOW_NO_MORE_VIEW = 2;//没有更多

    private int mType;

    public BaseFooterHolder(int viewLayout, ViewGroup parent) {
        super(viewLayout, parent);
    }

    abstract protected void setState(int type);

    @Override
    public void bindView(Integer itemData) {
        super.bindView(itemData);
        if (itemData == null) {
            mType = TYPE_LOADING;
        } else {
            mType = itemData;
        }

        setState(mType);
    }
}
