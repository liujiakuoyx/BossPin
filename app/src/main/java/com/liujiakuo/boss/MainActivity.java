package com.liujiakuo.boss;

import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;

import com.liujiakuo.boss.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Handler handler = new Handler();

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.liujiakuo.core.task.Core.task().call(new Runnable() {
            @Override
            public void run() {
                Log.d("TAGTAG", "run: " + (Looper.getMainLooper() == Looper.myLooper()) + " " + Thread.currentThread().getId());
            }
        }).execute();
        for (int i = 0; i < 20; i++) {
            com.liujiakuo.core.task.Core.task().call(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        Log.d("TAGTAG", "run: 3000 " + Thread.currentThread().getName() + "  " + Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).enqueue();
        }

        com.liujiakuo.core.task.Core.task().call(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Log.d("TAGTAG", "run: 3000 " + Thread.currentThread().getName() + "  " + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).enqueue();
    }
}
