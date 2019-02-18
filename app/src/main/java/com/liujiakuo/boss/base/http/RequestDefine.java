package com.liujiakuo.boss.base.http;

import com.liujiakuo.boss.base.BaseFragment;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by 佳阔 on 2019/2/17.
 * 创建网络请求的Request
 */

public class RequestDefine {
    private static final String BASE_URL = "http://10.236.128.218:8080/";
    private static final String JOB_URL = BASE_URL + "job/";
    private static final String GET_POSITION_URL = JOB_URL + "getJobList";

    /**
     * 获取工作列表
     *
     * @param page    当前页
     * @param refresh
     */
    public static Request getJobListRequest(BaseFragment fragment, int page, boolean refresh) {
        if (refresh) {
            page = 0;
        }
        FormBody body = new FormBody.Builder()
                .add("page", String.valueOf(page))
                .build();
        Request request = new Request.Builder()
                .url(GET_POSITION_URL)
                .tag(fragment)
                .post(body)
                .build();
        return request;
    }
}
