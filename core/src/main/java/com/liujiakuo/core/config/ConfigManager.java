package com.liujiakuo.core.config;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 佳阔 on 2019/3/23.
 */

public class ConfigManager {
    private static Context mContext;

    private static final String COMMA_REPLACEMENT = "__COMMA__";

    private static void checkContext() {
        if (mContext == null) {
            throw new IllegalStateException("context has not been initialed, please use #init()");
        }
    }

    public static void init(Application application) {
        mContext = application.getApplicationContext();
    }

    public synchronized static void save(String key, String value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_STRING_URI + key);
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, value);
        resolver.update(uri, cv, null, null);
    }

    public synchronized static void save(String key, boolean value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_BOOLEAN_URI + key);
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, value);
        resolver.update(uri, cv, null, null);
    }

    public synchronized static void save(String key, int value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_INT_URI + key);
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, value);
        resolver.update(uri, cv, null, null);
    }

    public synchronized static void save(String key, float value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_FLOAT_URI + key);
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, value);
        resolver.update(uri, cv, null, null);
    }

    public synchronized static void save(String key, long value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_LONG_URI + key);
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, value);
        resolver.update(uri, cv, null, null);
    }

    public synchronized static void save(String key, @NonNull Set<String> value) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_STRING_SET_URI + key);
        Set<String> set = new HashSet<>(value.size());
        for (String string : value) {
            set.add(string.replace(",", COMMA_REPLACEMENT));
        }
        ContentValues cv = new ContentValues();
        cv.put(ConstantUtil.VALUE, set.toString());
        resolver.update(uri, cv, null, null);
    }

    public static String getString(String key, String defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_STRING_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        return String.valueOf(rtn);
    }

    public static int getInt(String key, int defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_INT_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        return Integer.parseInt(rtn);
    }

    public static long getLong(String key, long defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_LONG_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        return Long.parseLong(rtn);
    }

    public static float getFloat(String key, float defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_FLOAT_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        return Float.parseFloat(rtn);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_BOOLEAN_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        return Boolean.parseBoolean(rtn);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_STRING_SET_URI + key);
        String rtn = resolver.getType(uri);
        if (rtn == null || rtn.equals(ConstantUtil.NULL_STRING)) {
            return defaultValue;
        }
        //是否符合“[1, 2, 3]”
        if (!rtn.matches("\\[.*\\]")) {
            return defaultValue;
        }
        Set<String> set = new HashSet<>();
        //截取数组部分
        String string = rtn.substring(1, rtn.length() - 1);
        String[] strings = string.split(", ");
        for (String str : strings) {
            //还原字符串
            set.add(str.replace(COMMA_REPLACEMENT, ","));
        }
        return set;
    }

    /**
     * 获取全部
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Map<String, ?> getAll() {
        checkContext();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.CONTENT_URI + ConstantUtil.SEPARATOR + ConstantUtil.TYPE_GET_ALL);
        Cursor cursor = resolver.query(uri, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Map<String, Object> resultMap = new HashMap();
            int nameIndex = cursor.getColumnIndex(ConstantUtil.CURSOR_COLUMN_NAME);
            int typeIndex = cursor.getColumnIndex(ConstantUtil.CURSOR_COLUMN_TYPE);
            int valueIndex = cursor.getColumnIndex(ConstantUtil.CURSOR_COLUMN_VALUE);
            do {
                String type = cursor.getString(typeIndex);
                String key = cursor.getString(nameIndex);
                Object value = null;
                if (type.equalsIgnoreCase(ConstantUtil.TYPE_STRING)) {
                    value = cursor.getString(valueIndex);
                    if (((String) value).matches("\\[.*\\]")) {
                        //是set数组
                        String strs = (String) value;
                        Set<String> set = new HashSet<>();
                        //截取数组部分
                        String string = strs.substring(1, strs.length() - 1);
                        String[] strings = string.split(", ");
                        for (String str : strings) {
                            //还原字符串
                            set.add(str.replace(COMMA_REPLACEMENT, ","));
                        }
                        value = set;
                    }
                } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_LONG)) {
                    value = cursor.getLong(valueIndex);
                } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_FLOAT)) {
                    value = cursor.getFloat(valueIndex);
                } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_BOOLEAN)) {
                    value = cursor.getString(valueIndex);
                } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_INT)) {
                    value = cursor.getInt(valueIndex);
                }
                resultMap.put(key, value);

            } while (cursor.moveToNext());
            cursor.close();
            return resultMap;
        }
        return null;
    }

    public static void remove(String key) {
        checkContext();
        ContentResolver cr = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.TYPE_LONG_URI + key);
        cr.delete(uri, null, null);
    }

    public static void clear() {
        checkContext();
        ContentResolver cr = mContext.getContentResolver();
        Uri uri = Uri.parse(ConstantUtil.CONTENT_URI + ConstantUtil.SEPARATOR + ConstantUtil.TYPE_CLEAN);
        cr.delete(uri, null, null);
    }

}
