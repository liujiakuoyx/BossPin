package com.liujiakuo.boss.base.http.response;

import java.io.Serializable;

/**
 * liujiakuo on 2019/2/18 11:45
 */
public class CodeResponse implements Serializable {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
