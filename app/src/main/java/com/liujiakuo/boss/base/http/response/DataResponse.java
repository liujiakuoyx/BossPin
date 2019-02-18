package com.liujiakuo.boss.base.http.response;

/**
 * liujiakuo on 2019/2/18 11:48
 */
public class DataResponse<T> extends MessageResponse {
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
