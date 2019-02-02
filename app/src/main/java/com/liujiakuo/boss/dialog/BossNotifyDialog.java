package com.liujiakuo.boss.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragmentDialog;
import com.liujiakuo.boss.utils.TextUtils;
import com.liujiakuo.boss.utils.ViewUtils;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class BossNotifyDialog extends BaseFragmentDialog {
    public static final String POSITIVE_TEXT_KEY = "positive_text_key";
    public static final String NEGATIVE_TEXT_KEY = "negative_text_key";
    public static final String TITLE_TEXT_KEY = "title_text_key";
    public static final String MESSAGE_TEXT_KEY = "message_text_key";
    private OnClickListener mPositiveListener;//确定按钮
    private OnClickListener mNegativeListener;//否认按钮
    private String mPositiveText;
    private String mNegativeText;
    private String mTitle;
    private String mMessage;


    @Override
    protected void initArguments(Bundle arguments) {
        super.initArguments(arguments);
        mPositiveText = arguments.getString(POSITIVE_TEXT_KEY, "");
        mNegativeText = arguments.getString(NEGATIVE_TEXT_KEY, "");
        if (TextUtils.isEmpty(mPositiveText) && TextUtils.isEmpty(mNegativeText)) {
            mPositiveText = getString(R.string.dialog_button_default_text);
        }
        mTitle = arguments.getString(TITLE_TEXT_KEY, "");
        mMessage = arguments.getString(MESSAGE_TEXT_KEY, "");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialogView(view);
    }

    private void initDialogView(View view) {
        initButton(view);//create buttons
        //create title view
        TextView titleView = view.findViewById(R.id.common_dialog_title);
        if (TextUtils.isEmpty(mTitle)) {
            ViewUtils.setViewGone(titleView);
        } else {
            titleView.setText(mTitle);
        }
        //create message view
        TextView messageView = view.findViewById(R.id.common_dialog_message);
        if (TextUtils.isEmpty(mMessage)) {
            ViewUtils.setViewGone(messageView);
        } else {
            messageView.setText(mMessage);
        }
    }

    /**
     * 构建按钮
     */
    private void initButton(View view) {
        if (TextUtils.isEmpty(mNegativeText)) {
            ViewUtils.setViewGone(view.findViewById(R.id.common_dialog_negative_container));
        } else {
            ViewUtils.setViewVisible(view.findViewById(R.id.common_dialog_negative_container));
            TextView negative = view.findViewById(R.id.common_dialog_negative_text);
            View negativeContainer = view.findViewById(R.id.common_dialog_negative_container);
            negative.setText(mNegativeText);
            negativeContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNegativeListener != null) {
                        mNegativeListener.onClick(v);
                    }
                    dismiss();
                }
            });

        }
        if (TextUtils.isEmpty(mPositiveText)) {
            ViewUtils.setViewGone(view.findViewById(R.id.common_dialog_positive_container));
        } else {
            ViewUtils.setViewVisible(view.findViewById(R.id.common_dialog_positive_container));
            TextView positive = view.findViewById(R.id.common_dialog_positive_text);
            View positiveContainer = view.findViewById(R.id.common_dialog_positive_container);
            positive.setText(mPositiveText);
            positiveContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPositiveListener != null) {
                        mPositiveListener.onClick(v);
                    }
                    dismiss();
                }
            });
        }
        //显示一个按钮，不显示分割线
        if (TextUtils.isEmpty(mPositiveText) || TextUtils.isEmpty(mNegativeText)) {
            ViewUtils.setViewGone(view.findViewById(R.id.common_dialog_button_line));
        }
    }

    public void setPositiveListener(OnClickListener mPositiveListener) {
        this.mPositiveListener = mPositiveListener;
    }

    public void setNegativeListener(OnClickListener mNegativeListener) {
        this.mNegativeListener = mNegativeListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_dialog_layout;
    }

    @Override
    protected float getWidth() {
        return 216f;
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    public static class Builder {
        private OnClickListener mPositiveListener;//确定按钮
        private OnClickListener mNegativeListener;//否认按钮
        private CharSequence mPositiveText;
        private CharSequence mNegativeText;
        private Bundle mData = new Bundle();
        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setPositiveButton(String text, OnClickListener positiveListener) {
            mPositiveListener = positiveListener;
            mData.putString(POSITIVE_TEXT_KEY, text);
            return this;
        }

        public Builder setNegativeButton(String text, OnClickListener negativeListener) {
            mNegativeListener = negativeListener;
            mData.putString(NEGATIVE_TEXT_KEY, text);
            return this;
        }

        public Builder setCancelable(boolean b) {
            mData.putBoolean(KEY_CANCELABLE, b);
            return this;
        }

        public Builder setCanBackEvent(boolean b) {
            mData.putBoolean(KEY_BACKEVENT, b);
            return this;
        }

        public Builder setTitle(String title) {
            mData.putString(TITLE_TEXT_KEY, title);
            return this;
        }

        public Builder setMessage(String msg) {
            mData.putString(MESSAGE_TEXT_KEY, msg);
            return this;
        }

        public BossNotifyDialog build() {
            BossNotifyDialog dialog = (BossNotifyDialog) BossNotifyDialog.instantiate(mContext,
                    BossNotifyDialog.class.getName(), mData);
            dialog.setNegativeListener(mNegativeListener);
            dialog.setPositiveListener(mPositiveListener);
            return dialog;
        }

    }
}
