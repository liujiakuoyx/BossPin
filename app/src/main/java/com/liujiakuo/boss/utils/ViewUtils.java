package com.liujiakuo.boss.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class ViewUtils {
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static void setViewGone(View view) {
        if (view == null) return;
        view.setVisibility(View.GONE);
    }

    public static void setViewVisible(View view) {
        if (view == null) return;
        view.setVisibility(View.VISIBLE);
    }
}
