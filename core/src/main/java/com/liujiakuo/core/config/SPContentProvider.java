package com.liujiakuo.core.config;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by 佳阔 on 2019/3/23.
 */

public class SPContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String path = uri.getPath();
        String[] paths = path.split(ConstantUtil.SEPARATOR);
        if (paths.length > 0 && paths[1].equals(ConstantUtil.TYPE_GET_ALL)) {
            Map<String, ?> all = SPHelperImpl.getAll(getContext());
            if (all != null && all.size() > 0) {
                MatrixCursor cursor = new MatrixCursor(new String[]{ConstantUtil.CURSOR_COLUMN_NAME,
                        ConstantUtil.CURSOR_COLUMN_TYPE,
                        ConstantUtil.CURSOR_COLUMN_VALUE});
                Set<String> keySet = all.keySet();
                for (String key : keySet) {
                    Object[] row = new Object[3];
                    row[0] = key;
                    row[2] = all.get(key);
                    if (row[2] instanceof String) {
                        row[1] = ConstantUtil.TYPE_STRING;
                    } else if (row[2] instanceof Boolean) {
                        row[1] = ConstantUtil.TYPE_BOOLEAN;
                    } else if (row[2] instanceof Integer) {
                        row[1] = ConstantUtil.TYPE_INT;
                    } else if (row[2] instanceof Float) {
                        row[1] = ConstantUtil.TYPE_FLOAT;
                    } else if (row[2] instanceof Long) {
                        row[1] = ConstantUtil.TYPE_LONG;
                    }
                    cursor.addRow(row);
                }
                return cursor;
            }
        }

        return null;
    }

    /**
     * 用这个方法取值
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String[] paths = uri.getPath().split(ConstantUtil.SEPARATOR);
        if (paths.length < 2) {
            return null;
        }
        String type = paths[1];
        String key = paths[2];
        if (type.equals(ConstantUtil.TYPE_CONTAIN)) {
            return String.valueOf(SPHelperImpl.contain(getContext(), key));
        }
        return SPHelperImpl.get(getContext(), key, type);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String path = uri.getPath();
        String[] paths = path.split(ConstantUtil.SEPARATOR);
        String type = paths[1];
        String key = paths[2];
        Object value = values.get(ConstantUtil.VALUE);
        if (value != null) {
            SPHelperImpl.save(getContext(), key, value);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String path = uri.getPath();
        String[] paths = path.split(ConstantUtil.SEPARATOR);
        String type = paths[1];
        if (type.equals(ConstantUtil.TYPE_CLEAN)) {
            //清空
            SPHelperImpl.clear(getContext());
            return 0;
        }
        String key = paths[2];
        //移除字段
        SPHelperImpl.remove(getContext(), key);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        insert(uri, values);
        return 0;
    }
}
