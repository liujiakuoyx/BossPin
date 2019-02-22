package com.liujiakuo.boss.topbar;

import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static com.liujiakuo.boss.topbar.TopBarComponentTag.TAG_UNSPECIFIED;

/**
 * liujiakuo on 2019/2/22 15:33
 */
public class CommonTopBarUtils {

    /**
     * 得到对应的topView
     */
    public static <T> void findAndDo(TopBarView parent, String tag, TopBarOp op) {
        if (op == null) {
            return;
        }
        List<T> viewList = findViewsByTag(parent, tag);
        if (viewList != null && !viewList.isEmpty()) {
            for (T view : viewList) {
                if (view != null) {
                    op.op(view);
                }
            }
        }
    }

    private static <T> List<T> findViewsByTag(TopBarView parent, String tag) {
        List<T> result = new ArrayList<>();
        if (TextUtils.isEmpty(tag) || TAG_UNSPECIFIED.equals(tag) || parent == null) {
            return result;
        }
        if (tag.equals(parent.getTopBarTag())) {
            result.add((T) parent);
        }
        if (parent instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) parent).getChildCount(); i++) {
                if (((ViewGroup) parent).getChildAt(i) instanceof TopBarView) {
                    result.addAll(CommonTopBarUtils.<T>findViewsByTag(((TopBarView) ((ViewGroup) parent).getChildAt(i)), tag));
                }
            }
        }
        return result;
    }
}
