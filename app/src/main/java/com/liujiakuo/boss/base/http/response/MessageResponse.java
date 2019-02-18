package com.liujiakuo.boss.base.http.response;

/**
 * liujiakuo on 2019/2/18 11:46
 */
public class MessageResponse extends CodeResponse {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
