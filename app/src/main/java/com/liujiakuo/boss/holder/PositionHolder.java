package com.liujiakuo.boss.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.list.BaseViewHolder;
import com.liujiakuo.boss.biz.position.PositionBean;

/**
 * liujiakuo on 2019/2/18 09:32
 */
public class PositionHolder extends BaseViewHolder<PositionBean> {
    public PositionHolder(int viewLayout, ViewGroup parent) {
        super(viewLayout, parent);
    }

    @Override
    public void bindView(PositionBean itemData) {
        super.bindView(itemData);
        TextView jName = getView(R.id.job_name);
        TextView cName = getView(R.id.c_name);
        TextView uName = getView(R.id.u_name);
        jName.setText(itemData.getName());
        cName.setText(itemData.getCompany().getName() + "");
        uName.setText(itemData.getUser().getNick() + "");
    }
}
