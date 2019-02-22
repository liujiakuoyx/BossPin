package com.liujiakuo.boss.topbar;

import android.content.Context;

/**
 * 将持有数据的TopBar转换成view类型的TopBar
 */
public class TopBarBuilder {
    /**
     * 将顶栏定义转化为view
     * @param context
     * @param topBarDefine
     * @return
     */
    public static BaseTopBarImpl buildTopBar(Context context, TopBar topBarDefine) {
        BaseTopBarImpl topBar = new BaseTopBarImpl(context);
        topBar.setData(topBarDefine);
        return topBar;
    }
}
