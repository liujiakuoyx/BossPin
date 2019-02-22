package com.liujiakuo.boss.topbar;


import android.view.View;

/**
 * liujiakuo on 2019/2/22 18:14
 * topbar中的子元素
 */
public interface TopBarElement {
    String tag();

    /**
     * 获取View实例
     * @return
     */
    View getSelf();
}
