package com.liujiakuo.core.task.call;

import android.support.annotation.NonNull;

import com.liujiakuo.core.task.CoreThreadPool;

/**
 * Created by 佳阔 on 2019/2/2.
 */

public class TaskCall implements Call<Void> {
    private Runnable mRunnable;
    private boolean mExecuted;
    private boolean mCanceled;

    public TaskCall(@NonNull Runnable r) {
        mRunnable = r;
    }

    @Override
    public void execute() {
        if (mRunnable == null) return;
        mRunnable.run();
    }

    @Override
    public void enqueue() {
        if (mRunnable == null) return;
        CoreThreadPool.enqueue(mRunnable);
    }

    @Override
    public void cancel() {
        mCanceled = true;
    }

}
