package com.liujiakuo.core.config;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佳阔 on 2019/3/23.
 */

public class SPHelperImpl {
    private static final String TAG = "SPHelperImpl";

    private static final String MAINSPNAME = "config_main";

    //内存缓存
    private static SoftReference<Map<String, Object>> sCacheMap;

    private static SharedPreferences getSP(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(MAINSPNAME, Context.MODE_PRIVATE);
    }

    public synchronized static <T> void save(Context context, String key, T t) {
        SharedPreferences sp = getSP(context);
        if (sp == null) {
            return;
        }
        //缓存中有，那么sp中必定有
        if (t.equals(getCachedValue(key))) {
            return;
        }
        SharedPreferences.Editor edit = sp.edit();
        if (t instanceof Integer) {
            edit.putInt(key, (Integer) t);
        } else if (t instanceof Float) {
            edit.putFloat(key, (Float) t);
        } else if (t instanceof Long) {
            edit.putLong(key, (Long) t);
        } else if (t instanceof String) {
            edit.putString(key, (String) t);
        } else if (t instanceof Boolean) {
            edit.putBoolean(key, (Boolean) t);
        }
        edit.commit();
        setValueToCached(key, t);
    }

    public static String get(Context context, String key, String type) {
        Object value = getCachedValue(key);
        if (value != null) {
            //缓存不为空
            return String.valueOf(value);
        } else {
            //缓存为空
            value = get_impl(context, key, type);
            //存入缓存
            setValueToCached(key, value);
            return String.valueOf(value);
        }
    }

    private static Object get_impl(Context context, String key, String type) {
        if (!contain(context, key)) {
            return null;
        }
        if (type.equalsIgnoreCase(ConstantUtil.TYPE_STRING)) {
            return getString(context, key, "");
        } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_STRING_SET)) {
            return getString(context, key, null);
        } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_INT)) {
            return getInt(context, key, 0);
        } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_LONG)) {
            return getLong(context, key, 0L);
        } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_FLOAT)) {
            return getFloat(context, key, 0f);
        } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_BOOLEAN)) {
            return getBoolean(context, key, false);
        }
        return null;
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getString(key, defaultValue);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getInt(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getBoolean(key, defaultValue);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getFloat(key, defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getLong(key, defaultValue);
    }

    public static boolean contain(Context context, String key) {
        SharedPreferences sp = getSP(context);
        if (sp == null) {
            return false;
        }
        return sp.contains(key);
    }

    private static void setValueToCached(String key, Object value) {
        Map<String, Object> map;
        if (sCacheMap == null) {
            //软引用为空，新建软引用
            map = new HashMap<>();
            sCacheMap = new SoftReference<Map<String, Object>>(map);
        } else {
            map = sCacheMap.get();
            if (map == null) {
                //取不到初始化map
                map = new HashMap<>();
                sCacheMap = new SoftReference<Map<String, Object>>(map);
            }
        }
        map.put(key, value);
    }

    /**
     * 拿到缓存的值
     */
    private static Object getCachedValue(String key) {
        if (sCacheMap != null) {
            Map<String, Object> map = sCacheMap.get();
            if (map != null) {
                return map.get(key);
            }
        }
        return null;
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = getSP(context);
        if (sp != null) {
            SharedPreferences.Editor edit = sp.edit();
            edit.remove(key);
            edit.commit();
        }
    }

    public static void clear(Context context) {
        SharedPreferences sp = getSP(context);
        if (sp != null) {
            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.commit();
        }
        cleanCachedValue();
    }

    /**
     * 清空缓存
     */
    private static void cleanCachedValue() {
        Map<String, Object> map;
        if (sCacheMap != null) {
            map = sCacheMap.get();
            if (map != null) {
                map.clear();
            }
        }
    }

    /**
     * 获取全部字段
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = getSP(context);
        if (sp != null) {
            return sp.getAll();
        }
        return null;
    }
}
