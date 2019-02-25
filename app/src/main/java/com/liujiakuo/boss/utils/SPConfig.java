package com.liujiakuo.boss.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * liujiakuo on 2019/2/25 14:12
 */
public class SPConfig {
    private static final String DEFAULT_NAME = "config";
    private static SharedPreferences mSp;
    private static SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (mSp == null || mEditor == null) {
            mSp = context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE);
            mEditor = mSp.edit();
        }
    }

    public static void putString(String key, String value) {
        if (mEditor != null) {
            synchronized (mEditor) {
                mEditor.putString(key, value);
                mEditor.commit();
            }
        }
    }

    public static void putInt(String key, int value) {
        if (mEditor != null) {
            synchronized (mEditor) {
                mEditor.putInt(key, value);
                mEditor.commit();
            }
        }
    }

    public static void putLong(String key, long value) {
        if (mEditor != null) {
            synchronized (mEditor) {
                mEditor.putLong(key, value);
                mEditor.commit();
            }
        }
    }

    public static void putFloat(String key, float value) {
        if (mEditor != null) {
            synchronized (mEditor) {
                mEditor.putFloat(key, value);
                mEditor.commit();
            }
        }
    }

    public static void putBoolean(String key, boolean value) {
        if (mEditor != null) {
            synchronized (mEditor) {
                mEditor.putBoolean(key, value);
                mEditor.commit();
            }
        }
    }

    public static String getString(String key) {
        if (mSp != null) {
            synchronized (mSp) {
                return mSp.getString(key, "");
            }
        }
        return "";
    }

    public static boolean getBoolean(String key) {
        if (mSp != null) {
            synchronized (mSp) {
                return mSp.getBoolean(key, false);
            }
        }
        return false;
    }

    public static long getLong(String key) {
        if (mSp != null) {
            synchronized (mSp) {
                return mSp.getLong(key, 0);
            }
        }
        return 0;
    }

    public static float getFloat(String key) {
        if (mSp != null) {
            synchronized (mSp) {
                return mSp.getFloat(key, 0f);
            }
        }
        return 0f;
    }

    public static int getInt(String key) {
        if (mSp != null) {
            synchronized (mSp) {
                return mSp.getInt(key, 0);
            }
        }
        return 0;
    }

}
