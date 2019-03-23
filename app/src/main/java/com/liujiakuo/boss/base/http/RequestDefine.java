package com.liujiakuo.boss.base.http;

import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.utils.TextUtils;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 佳阔 on 2019/2/17.
 * 创建网络请求的Request
 */

public class RequestDefine {
    private static final String BASE_URL = "http://123.206.41.242:8080/";
    private static final String JOB_URL = BASE_URL + "job/";
    private static final String USER_URL = BASE_URL + "user/";
    private static final String GET_POSITION_URL = JOB_URL + "getJobList";
    private static final String GET_POSITION_DEFAULT_URL = JOB_URL + "jobDetail";
    private static final String LOGIN_URL = USER_URL + "login";
    private static final String REGISTER_URL = USER_URL + "register";

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

    /**
     * 获取职位详细信息
     */
    public static Request getPositionDefault(BaseFragment fragment, String posId) {
        if (TextUtils.isEmpty(posId)) {
            return null;
        }
        FormBody body = new FormBody.Builder()
                .add("jid", String.valueOf(posId))
                .build();
        Request request = new Request.Builder()
                .url(GET_POSITION_DEFAULT_URL)
                .tag(fragment)
                .post(body)
                .build();
        return request;
    }

    /**
     * 登录
     */
    public static Request getLoginRequest(BaseFragment fragment, String name, String pass) {
        FormBody body = new FormBody.Builder()
                .add("key", name)
                .add("pass", pass)
                .build();
        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .tag(fragment)
                .post(body)
                .build();
        return request;
    }

    /**
     * 注册
     */
    public static Request getRegisterRequest(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(REGISTER_URL)
                .post(body)
                .build();
        return request;
    }
}
