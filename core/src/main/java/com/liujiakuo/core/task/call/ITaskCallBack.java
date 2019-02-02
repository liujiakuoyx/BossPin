package com.liujiakuo.core.task.call;

/**
 * Created by 佳阔 on 2019/2/2.
 * 任务回调，暂时无用
 */

public interface ITaskCallBack<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}
