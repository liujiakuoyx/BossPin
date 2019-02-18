package com.liujiakuo.boss.base.http.response;

/**
 * liujiakuo on 2019/2/18 11:49
 */
public class PageDataResponse<T> extends DataResponse<T> {
    private int page;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
