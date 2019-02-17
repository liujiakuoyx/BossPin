package com.liujiakuo.boss.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by 佳阔 on 2019/2/8.
 * 单一fragment启动类
 */

public class SingleFragmentHelper {
    public static final String FRAGMENT_NAME = "fragment_name";
    public static final String FRAGMENT_TAG = "fragment_tag";
    public static final String FRAGMENT_ARGU = "fragment_argu";

    public static void startSingleFragment(Context context, String fragmentName, String fragmentTag, Bundle bundle) {
        Intent intent = new Intent(context, SingleFragmentActivity.class);
        putSingleBundle(bundle, intent, fragmentName, fragmentTag);
    }

    private static void putSingleBundle(Bundle bundle, Intent intent, String fragmentName, String fragmentTag) {
        if (intent == null) {
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();//防止bundle为空crash
        }
        intent.putExtra(FRAGMENT_NAME, fragmentName);
        intent.putExtra(FRAGMENT_TAG, fragmentTag);
        intent.putExtra(FRAGMENT_ARGU, bundle);
    }

    public static Fragment addFragmentByTag(Context context, @NonNull FragmentManager fm, int container, String clazz, String tag, Bundle argument) {
        Fragment f = fm.findFragmentByTag(tag);
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            f = Fragment.instantiate(context, clazz, argument);
            ft.add(container, f, tag);
            ft.commit();
        } else if (f.isDetached()) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(f);
            ft.commit();
        }
        return f;
    }
}
