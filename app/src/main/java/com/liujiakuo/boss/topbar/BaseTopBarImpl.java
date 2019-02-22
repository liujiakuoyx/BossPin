package com.liujiakuo.boss.topbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.liujiakuo.boss.R;

import java.util.ArrayList;
import java.util.List;

/**
 * liujiakuo on 2019/2/22 15:24
 * 设计思路
 * BaseTopBarImpl 表示了一个topbar的基础容器
 * TopBarElement代表了一个完整的topbar视图，每个时刻只能有一个视图显示（为了适应碎片布局切换tab改变topbar布局样式）
 */
public class BaseTopBarImpl extends FrameLayout implements TopBarEventCallback, TopBarView {
    private List<TopBarElement> mTopBarElementList = new ArrayList<>();

    public BaseTopBarImpl(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseTopBarImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTopBarImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setId(R.id.top_bar);
    }

    /**
     * 处理内部view传进来的事件
     *
     * @param eventId 事件id
     * @param data    事件数据
     * @return 是否被处理
     */
    @Override
    public boolean handleEvent(int eventId, Object data) {
        return false;
    }


    public <T> void findAndDo(String tag, TopBarOp<T> op) {
        CommonTopBarUtils.findAndDo(this, tag, op);
    }

    @Override
    public String tag() {
        return null;
    }

    @Override
    public View getSelf() {
        return this;
    }

    @Override
    public String getTopBarTag() {
        return TopBarComponentTag.TAG_TOP_BAR;
    }

    @Override
    public TopBarEventCallback getCallback() {
        return this;
    }

    /**
     * 展示一个状态
     */
    public void switchState(int toState) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(GONE);
        }
        if (toState < getChildCount()) {
            getChildAt(toState).setVisibility(VISIBLE);
        }
    }

    public void setData(TopBar data) {
    }
}
