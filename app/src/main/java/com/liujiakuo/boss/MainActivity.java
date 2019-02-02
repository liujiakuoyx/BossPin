package com.liujiakuo.boss;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liujiakuo.boss.base.BaseActivity;
import com.liujiakuo.boss.base.BaseFragmentDialog;
import com.liujiakuo.boss.dialog.BossNotifyDialog;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BossNotifyDialog.Builder builder = new BossNotifyDialog.Builder(this);
        BossNotifyDialog dialog = builder
                .setCancelable(false)
                .setCanBackEvent(false)
                .setTitle("HHHHHH")
                .setMessage("hhhhhhhhhhhhhhhhhhh")
                .setNegativeButton("111", new BossNotifyDialog.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("222", new BossNotifyDialog.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "222", Toast.LENGTH_SHORT).show();
                    }
                }).build();
        dialog.show(getSupportFragmentManager(), "1");
    }
}
