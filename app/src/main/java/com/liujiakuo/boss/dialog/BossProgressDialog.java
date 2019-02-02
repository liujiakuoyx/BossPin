package com.liujiakuo.boss.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.layers.LottieDrawable;
import com.airbnb.lottie.model.LottieComposition;
import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragmentDialog;
import com.liujiakuo.boss.base.LottieRes;

/**
 * Created by 佳阔 on 2019/2/1.
 */

public class BossProgressDialog extends BaseFragmentDialog {

    private LottieDrawable mLoadingDrawable;

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (view == null) return;
        final ImageView circleView = view.findViewById(R.id.progress_dialog_circle);
        LottieComposition.fromAssetFileName(getContext(), LottieRes.BASE_DIALOG_LOADING, new LottieComposition.OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                mLoadingDrawable = new LottieDrawable();
                circleView.setImageDrawable(mLoadingDrawable);
                mLoadingDrawable.setComposition(composition);
                mLoadingDrawable.setProgress(0f);
                mLoadingDrawable.loop(true);
                mLoadingDrawable.playAnimation();
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLoadingDrawable != null && mLoadingDrawable.isAnimating()) {
            mLoadingDrawable.cancelAnimation();
            mLoadingDrawable = null;
        }
    }

    @Override
    protected void initArguments(Bundle arguments) {
        super.initArguments(arguments);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.progress_dialog_layout;
    }

    @Override
    protected float getWidth() {
        return 136f;
    }
}
