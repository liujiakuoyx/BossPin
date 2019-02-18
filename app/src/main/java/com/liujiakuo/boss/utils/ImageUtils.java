package com.liujiakuo.boss.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * liujiakuo on 2019/2/18 20:01
 */
public class ImageUtils {

    public static void loadImage(String url, ImageView view) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
