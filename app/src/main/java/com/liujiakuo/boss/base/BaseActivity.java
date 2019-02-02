package com.liujiakuo.boss.base;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.liujiakuo.boss.R;

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
     * Base层弹出dialog
     * @param type dialog类型
     * @param msg dialog内容
     */
    public void showDialog(int type, String msg){

    }
}
