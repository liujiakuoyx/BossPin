package com.liujiakuo.core.config;

public class ConstantUtil {
    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "com.liujiakuo.core.config.sphelper";
    public static final String SEPARATOR = "/";
    public static final String CONTENT_URI = CONTENT + AUTHORITY;
    public static final String TYPE_STRING_SET = "string_set";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_INT = "int";
    public static final String TYPE_LONG = "long";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String VALUE = "value";

    public static final String NULL_STRING = "null";
    public static final String TYPE_CONTAIN = "contain";
    public static final String TYPE_CLEAN = "clean";
    public static final String TYPE_GET_ALL = "get_all";

    public static final String CURSOR_COLUMN_NAME = "cursor_name";
    public static final String CURSOR_COLUMN_TYPE = "cursor_type";
    public static final String CURSOR_COLUMN_VALUE = "cursor_value";

    public static final String TYPE_STRING_URI = CONTENT_URI + SEPARATOR + TYPE_STRING + SEPARATOR;
    public static final String TYPE_BOOLEAN_URI = CONTENT_URI + SEPARATOR + TYPE_BOOLEAN + SEPARATOR;
    public static final String TYPE_FLOAT_URI = CONTENT_URI + SEPARATOR + TYPE_FLOAT + SEPARATOR;
    public static final String TYPE_LONG_URI = CONTENT_URI + SEPARATOR + TYPE_LONG + SEPARATOR;
    public static final String TYPE_INT_URI = CONTENT_URI + SEPARATOR + TYPE_INT + SEPARATOR;
    public static final String TYPE_STRING_SET_URI = CONTENT_URI + SEPARATOR + TYPE_STRING_SET + SEPARATOR;
}
