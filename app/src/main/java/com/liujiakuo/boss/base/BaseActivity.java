package com.liujiakuo.boss.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.liujiakuo.boss.dialog.BossProgressDialog;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
    }

    public abstract int getContentLayoutId();

    /**
     * 弹出加载dialog
     */
    public void showProgressDialog() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(BossProgressDialog.class.getName());
        if (fragment != null) {
            //已经显示
            return;
        }
        BossProgressDialog dialog = new BossProgressDialog();
        dialog.show(getSupportFragmentManager(), BossProgressDialog.class.getName());
    }

    /**
     * 隐藏加载框
     */
    public void dismissProgressDialog() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(BossProgressDialog.class.getName());
        if (fragment != null) {
            //移除加载弹窗
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
