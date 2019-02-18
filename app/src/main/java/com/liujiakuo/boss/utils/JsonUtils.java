package com.liujiakuo.boss.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * liujiakuo on 2019/2/18 15:26
 */
public class JsonUtils {
    private static Gson gson = new Gson();

    /**
     * 序列化json
     *
     * @param json  json串
     * @param token 数据模型
     */
    public static <T> T fromJson(String json, TypeToken<T> token) {
        return gson.fromJson(json, token.getType());
    }

    public static String toJson(Object data) {
        return toJson(gson, data);
    }

    public static String toJson(Gson gson, Object data) {
        if (gson == null || data == null) {
            return null;
        }
        return gson.toJson(data);
    }


}


