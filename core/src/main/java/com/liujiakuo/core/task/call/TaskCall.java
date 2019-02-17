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
    public Void execute() {
        if (mCanceled || mExecuted || mRunnable == null) {
            return null;
        }
        //同步执行
        mRunnable.run();
        mExecuted = true;
        return null;
    }

    @Override
    public Void enqueue() {
        if (mCanceled || mExecuted || mRunnable == null) {
            return null;
        }
        //交给线程池执行
        CoreThreadPool.execute(mRunnable);
        return null;
    }

    @Override
    public void cancel() {
        if (!mCanceled)
            mCanceled = true;
    }

}
