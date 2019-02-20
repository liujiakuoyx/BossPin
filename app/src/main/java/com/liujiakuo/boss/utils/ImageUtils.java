package com.liujiakuo.boss.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * liujiakuo on 2019/2/18 20:01
 */
public class ImageUtils {

    public static void loadImage(String url, ImageView view) {
        if (TextUtils.isEmpty(url) || view == null) {
            return;
        }
        Glide.with(view.getContext()).load(url).into(view);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(String url, ImageView view) {
        if (TextUtils.isEmpty(url) || view == null) {
            return;
        }
        Glide.with(view.getContext()).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(view);
    }
}
