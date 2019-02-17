package com.liujiakuo.core.task.call;


/**
 * Created by 佳阔 on 2019/2/2.
 * call基类
 */

public interface Call<T> extends Cloneable{
    T execute();

    T enqueue();

    void cancel();
}
