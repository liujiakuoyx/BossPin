package com.liujiakuo.boss.topbar;

import android.view.View;
import android.view.ViewGroup;

import com.liujiakuo.boss.R;


public class CellUtils {

    /**
     * 处理有边框button的margin
     * 顶栏的元素，视觉间距固定11dp，平分给两个元素。
     * 无边框icon按钮和文字按钮，设置了间距处理点击区域
     * 对于有边框的按钮或者自定义view，要设置通用的margin处理间距。
     * @param view
     */
    public static void adjustButtonMargin(View view) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).leftMargin
                    = (int) view.getResources().getDimension(R.dimen.top_bar_btn_default_space_lr);
            ((ViewGroup.MarginLayoutParams) params).rightMargin
                    = (int) view.getResources().getDimension(R.dimen.top_bar_btn_default_space_lr);
            view.setLayoutParams(params);
        }
    }
}
