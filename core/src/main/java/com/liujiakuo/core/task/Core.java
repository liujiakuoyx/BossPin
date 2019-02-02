package com.liujiakuo.core.task;

import android.support.annotation.NonNull;

import com.liujiakuo.core.task.call.Call;
import com.liujiakuo.core.task.call.TaskCall;

/**
 * Created by 佳阔 on 2019/2/2.
 */

public class Core {
    private static Task task;

    //拿到task
    public synchronized static Task task() {
        if (task == null) {
            task = new Task();
        }
        return task;
    }

    /**
     * 负责异步线程等任务执行，现在只有一个方法
     */
    public static class Task {
        //同步、异步任务
        public Call<Void> call(@NonNull Runnable r) {
            return new TaskCall(r);
        }
    }
}
