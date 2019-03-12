package com.liujiakuo.boss.config;

import com.liujiakuo.boss.biz.login.UserBean;
import com.liujiakuo.boss.utils.SPConfig;

/**
 * Created by 佳阔 on 2019/3/11.
 */

public class ConfigData {
    private static final String SP_CONFIG_KEY_USER_KEY = "sp_config_key_user_key";
    private static final String SP_CONFIG_KEY_USER_NAME = "sp_config_key_user_name";
    private static final String SP_CONFIG_KEY_USER_TYPE = "sp_config_key_user_type";
    private static final String SP_CONFIG_KEY_USER_LOGIN_STATUS = "sp_config_key_user_login_status";

    public static void putLoginInfo(UserBean userInfo) {
        if (userInfo == null) {
            return;
        }
        SPConfig.putBoolean(SP_CONFIG_KEY_USER_LOGIN_STATUS, true);
        SPConfig.putString(SP_CONFIG_KEY_USER_KEY, userInfo.getKey());
        SPConfig.putString(SP_CONFIG_KEY_USER_NAME, userInfo.getNick());
        SPConfig.putInt(SP_CONFIG_KEY_USER_TYPE, userInfo.getType());
    }

    public static void logout() {
        SPConfig.putBoolean(SP_CONFIG_KEY_USER_LOGIN_STATUS, false);
    }

    public static boolean isLogin() {
        return SPConfig.getBoolean(SP_CONFIG_KEY_USER_LOGIN_STATUS);
    }
}
