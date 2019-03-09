package com.liujiakuo.boss.biz.position;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.fragment.BaseRequestFragment;
import com.liujiakuo.boss.base.http.RequestDefine;
import com.liujiakuo.boss.base.http.response.DataResponse;
import com.liujiakuo.boss.biz.company.CompanyInfo;
import com.liujiakuo.boss.biz.user.UserInfo;
import com.liujiakuo.boss.utils.ImageUtils;
import com.liujiakuo.boss.utils.JsonUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.IParseNetwork;

/**
 * Created by 佳阔 on 2019/3/9.
 */

public class PositionDefaultFragment extends BaseRequestFragment<DataResponse<PositionBean>> {
    public static final String POSITION_ID = "position_id";
    public final String DOT = "·";
    private TextView mPositionName;
    private TextView mLocationText;
    private TextView mWorkYears;
    private TextView mEducationText;
    private TextView mSalaryText;//薪水
    private TextView mBossName;
    private TextView mBossPost;//职务
    private TextView mPositionDetails;//职位要求
    private TextView mCompanyName;
    private TextView mCompanyInfo;
    private ImageView mPosterHead;
    private ImageView mCompanyHead;

    private String mPositId;

    @Override
    protected int getContentView() {
        return R.layout.biz_position_default_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mPositId = arguments.getString(POSITION_ID);
    }

    @Override
    protected void onResponse(DataResponse<PositionBean> response) {
        PositionBean data = response.getData();
        if (data == null) {
            return;
        }
        UserInfo user = data.getUser();
        CompanyInfo company = data.getCompany();
        if (user != null) {
            mBossName.setText(user.getNick());
            mBossPost.setText(user.getPost());
            ImageUtils.loadCircleImage(user.getHeadUrl(), mPosterHead);
        }
        if (company != null) {
            mCompanyName.setText(company.getName());
            mCompanyInfo.setText(company.getStage() + DOT + company.getNumber());
            ImageUtils.loadCircleImage(company.getHeadUrl(), mCompanyHead);
        }
        mPositionName.setText(data.getName());
        mLocationText.setText(data.getLocation());
        mEducationText.setText(data.getEducation());
        mPositionDetails.setText(data.getDetails());
        mWorkYears.setText(data.getWorkYears());
        mSalaryText.setText(data.getSalary());

    }

    @Override
    protected void initViews(View view) {
        mLocationText = view.findViewById(R.id.position_default_location);
        mPositionName = view.findViewById(R.id.position_default_name);
        mWorkYears = view.findViewById(R.id.position_default_work_years);
        mEducationText = view.findViewById(R.id.position_default_education);
        mSalaryText = view.findViewById(R.id.position_default_salary);
        mBossName = view.findViewById(R.id.position_default_poster_name);
        mBossPost = view.findViewById(R.id.position_default_poster_info);
        mPositionDetails = view.findViewById(R.id.position_default_info);
        mCompanyName = view.findViewById(R.id.position_default_company_name);
        mCompanyInfo = view.findViewById(R.id.position_default_company_info);
        mPosterHead = view.findViewById(R.id.position_default_user_head);
        mCompanyHead = view.findViewById(R.id.position_default_company_head);
        TextView topBarTitle = view.findViewById(R.id.top_bar_title);
        topBarTitle.setText("职位信息");
    }


    /**
     * 处理返回数据
     */
    @Override
    public CommonRequest<DataResponse<PositionBean>> createRequest(boolean isRefresh) {
        CommonRequest<DataResponse<PositionBean>> request = new CommonRequest<>(RequestDefine.getPositionDefault(this, mPositId),
                new IParseNetwork<DataResponse<PositionBean>>() {
                    @Override
                    public DataResponse<PositionBean> parseNetworkResponse(String jsonStr) {
                        return JsonUtils.fromJson(jsonStr, new TypeToken<DataResponse<PositionBean>>() {
                        });
                    }
                });
        return request;
    }
}
