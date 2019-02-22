package com.liujiakuo.boss.topbar;

/**
 * 顶栏事件回调，内部组件与顶栏根布局通信用
 */
public interface TopBarEventCallback {

    boolean handleEvent(int eventId, Object data);
}
