package com.liujiakuo.boss.base.list;

import android.view.ViewGroup;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.holder.PositionHolder;
import com.liujiakuo.boss.holder.RecyclerViewItemType;
import com.liujiakuo.boss.utils.TextUtils;

/**
 * Created by 佳阔 on 2019/2/17.
 * 这个类负责把列表数据dataType转换成itemType
 */

public class HolderItemTypeFactory {

    /**
     * holderType类型，从后台转换
     */
    public static final String DATA_TYPE_JOB = "job";//职位类型
    public static final String DATA_TYPE_COMPANY = "company";//公司类型

    /**
     * 得到数据对应的type
     */
    public static int getHolderType(String dataType) {
        if (TextUtils.isEmpty(dataType)) {
            return 0;
        }
        return 0;
    }

    /**
     * 生成列表holder
     */
    public static BaseViewHolder generateViewHolder(ViewGroup parent, int type) {
        BaseViewHolder viewHolder = null;
        switch (type) {
            case RecyclerViewItemType.ITEM_TYPE_JOB:
                //工作列表holder
                viewHolder = new PositionHolder(R.layout.biz_item_position_holder, parent);
                break;
            case RecyclerViewItemType.ITEM_TYPE_COMPANY:
                break;
        }
        return viewHolder;
    }
}
