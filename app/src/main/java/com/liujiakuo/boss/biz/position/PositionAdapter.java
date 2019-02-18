package com.liujiakuo.boss.biz.position;


import android.view.ViewGroup;

import com.liujiakuo.boss.base.list.BasePageListAdapter;
import com.liujiakuo.boss.base.list.BaseViewHolder;
import com.liujiakuo.boss.base.list.HolderItemTypeFactory;
import com.liujiakuo.boss.holder.PositionHolder;
import com.liujiakuo.boss.holder.RecyclerViewItemType;

/**
 * liujiakuo on 2019/2/18 11:52
 */
public class PositionAdapter extends BasePageListAdapter<PositionBean, Void> {
    @Override
    protected BaseViewHolder createBasicViewHolder(ViewGroup parent, int viewType) {
        return HolderItemTypeFactory.generateViewHolder(parent, viewType);
    }

    @Override
    public int getBasicItemType(PositionBean basicItem) {
        return RecyclerViewItemType.ITEM_TYPE_JOB;
    }
}
