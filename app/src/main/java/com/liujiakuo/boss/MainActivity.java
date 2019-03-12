package com.liujiakuo.boss;

import android.os.Handler;
import android.os.Bundle;

import com.liujiakuo.boss.base.activity.BaseActivity;
import com.liujiakuo.boss.biz.ViewPageFragment;
import com.liujiakuo.boss.biz.position.PositionListFragment;

public class MainActivity extends BaseActivity {
    private Handler handler = new Handler();

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPageFragment mainFragment = new ViewPageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout,
                mainFragment, "ViewPageFragment").commitAllowingStateLoss();
    }
}
