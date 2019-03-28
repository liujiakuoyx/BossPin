package com.liujiakuo.boss.biz.profile;

import android.view.View;
import android.widget.ImageView;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.config.ConfigData;
import com.liujiakuo.boss.utils.ImageUtils;

/**
 * Created by 佳阔 on 2019/3/24.
 */

public class ProfileFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.biz_profile_fragment;
    }

    @Override
    protected void initViews(View view) {
        ImageView headView = view.findViewById(R.id.profile_head);
        ImageUtils.loadCircleImage(ConfigData.getHeadUrl(), headView);
    }
}
