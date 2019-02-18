package com.liujiakuo.boss.utils;

import android.content.res.Resources;
import android.util.TypedValue;


public class ScreenUtils {


    public static float dp2px(Resources resources, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
