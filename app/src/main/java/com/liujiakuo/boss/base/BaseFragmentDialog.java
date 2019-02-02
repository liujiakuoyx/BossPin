package com.liujiakuo.boss.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.utils.ViewUtils;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public abstract class BaseFragmentDialog extends DialogFragment {
    public static final String KEY_CANCELABLE = "key_cancelable";
    public static final String KEY_BACKEVENT = "key_backevent";
    public static final int YTPE_LOADIND = 0;//加载
    public static final int YTPE_PROMPT = 1;//提示
    public static final float DEFAULT_DIM_AMOUNT = 0.5f;
    protected boolean mCancelable = false;//点击外部消失
    protected boolean mBackEvent = false;//点击返回键返回

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        if (getArguments() != null)
            initArguments(getArguments());
    }

    protected void initArguments(Bundle arguments) {
        mCancelable = arguments.getBoolean(KEY_CANCELABLE, false);
        mBackEvent = arguments.getBoolean(KEY_BACKEVENT, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setCustomWindow(dialog.getWindow());
        setEvent(dialog);
        return dialog;

    }

    private void setEvent(Dialog dialog) {
        dialog.setCancelable(mCancelable);
        dialog.setCanceledOnTouchOutside(mCancelable);
        //拦截返回键事件
        if (!mBackEvent) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container);
    }

    //R.layout.common_dialog_layout
    protected abstract int getLayoutId();

    /**
     * 设置样式
     */
    protected void setCustomWindow(@NonNull Window window) {
        float dimAmount = DEFAULT_DIM_AMOUNT;
        window.setDimAmount(dimAmount);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewUtils.Dp2Px(getContext(), getWidth()),
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    /**
     * 宽度
     */
    protected abstract float getWidth();
}
