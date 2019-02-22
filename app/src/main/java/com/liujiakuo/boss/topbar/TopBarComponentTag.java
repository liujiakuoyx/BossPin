package com.liujiakuo.boss.topbar;

/**
 * liujiakuo on 2019/2/22 15:05
 * topbar组件，topbar分为left、right、middle三部分
 */
public class TopBarComponentTag {
    public static final String COMPONENT_LEFT = "component_left"; // 普通ActionBar左侧组件
    public static final String COMPONENT_RIGHT = "component_right"; //普通ActionBar右侧组件
    public static final String COMPONENT_MIDDLE = "component_middle"; //普通ActionBar中间组件

    public static final String TAG_UNSPECIFIED = ""; // 未设置id
    public static final String TAG_TOP_BAR = "top_bar"; // Top Bar的id
    public static final String TAG_BACK = "top_bar_back"; // Top Bar返回键
    public static final String TAG_TITLE = "top_bar_title"; // Top Bar标题id
    public static final String TAG_LINE_TAB = "top_bar_line_tab"; // Top Bar line tab的id
}
