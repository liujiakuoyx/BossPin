package com.liujiakuo.boss.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.list.BaseViewHolder;
import com.liujiakuo.boss.biz.position.PositionBean;
import com.liujiakuo.boss.utils.ImageUtils;

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
        TextView positionName = getView(R.id.position_name);
        TextView positionLocation = getView(R.id.position_location);
        TextView positionCompanyName = getView(R.id.position_company_name);
        TextView positionCompanyStage = getView(R.id.position_company_stage);
        TextView positionEducation = getView(R.id.position_education);
        TextView positionSalary = getView(R.id.position_salary);
        TextView positionWorkYears = getView(R.id.position_work_years);
        ImageView posterHead = getView(R.id.position_poster_head);
        TextView posterNick = getView(R.id.position_poster_nick);

        positionName.setText(itemData.getName());
        positionLocation.setText(itemData.getLocation());
        if (itemData.getCompany() != null) {
            positionCompanyName.setText(itemData.getCompany().getName());
            positionCompanyStage.setText(itemData.getCompany().getStage());
        }
        positionSalary.setText(itemData.getSalary());
        positionWorkYears.setText(itemData.getWorkYears());
        positionEducation.setText(itemData.getEducation());
        if (itemData.getUser() != null) {
            getView(R.id.position_poster_layout).setVisibility(View.VISIBLE);
            ImageUtils.loadCircleImage(itemData.getUser().getHeadUrl(), posterHead);
            posterNick.setText(itemData.getUser().getNick());
        } else {
            getView(R.id.position_poster_layout).setVisibility(View.GONE);
        }
    }
}
