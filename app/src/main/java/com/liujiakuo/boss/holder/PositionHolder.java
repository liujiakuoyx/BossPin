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
        TextView positionName = getView(R.id.position_name);
        TextView positionLocation = getView(R.id.position_location);
        TextView positionCompanyName = getView(R.id.position_company_name);
        TextView positionCompanyStage = getView(R.id.position_company_stage);
        TextView positionEducation = getView(R.id.position_education);
        TextView positionSalary = getView(R.id.position_salary);
        TextView positionWorkYears = getView(R.id.position_work_years);

        positionName.setText(itemData.getName());
        positionLocation.setText(itemData.getLocation());
        if (itemData.getCompany() != null) {
            positionCompanyName.setText(itemData.getCompany().getName());
            positionCompanyStage.setText(itemData.getCompany().getStage());
        }
        positionSalary.setText(itemData.getSalary());
        positionWorkYears.setText(itemData.getWorkYears());
        positionEducation.setText(itemData.getEducation());
    }
}
