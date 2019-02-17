package com.liujiakuo.boss.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.liujiakuo.boss.R;

/**
 * Created by 佳阔 on 2019/2/8.
 */

public class SingleFragmentActivity extends BaseActivity {
    private final int CONTAINER_ID = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null) {
            finish();
        }
        startFragment(getIntent());//启动fragment
    }

    private void startFragment(Intent intent) {
        String fragmentName = intent.getStringExtra(SingleFragmentHelper.FRAGMENT_NAME);
        String fragmentTag = intent.getStringExtra(SingleFragmentHelper.FRAGMENT_TAG);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment == null) {
            //没有添加过
            Bundle extra = intent.getBundleExtra(SingleFragmentHelper.FRAGMENT_ARGU);
            //添加fragment
            SingleFragmentHelper.addFragmentByTag(this, getSupportFragmentManager(),
                    CONTAINER_ID, fragmentName, fragmentTag, extra);
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.simple_fragment_activity_layout;
    }


}
