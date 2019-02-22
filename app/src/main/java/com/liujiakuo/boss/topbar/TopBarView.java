package com.liujiakuo.boss.topbar;


/**
 * topBar视图顶级元素
 */
public interface TopBarView extends TopBarElement{

    /**
     * 获取元素的tag
     * @return
     */
    String getTopBarTag();

    /**
     * 获取callback回调
     * @return
     */
    TopBarEventCallback getCallback();
}
