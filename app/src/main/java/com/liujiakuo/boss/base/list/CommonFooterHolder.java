package com.liujiakuo.boss.base.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liujiakuo.boss.R;

/**
 * liujiakuo on 2019/2/18 13:14
 */
public class CommonFooterHolder extends BaseFooterHolder {

    public CommonFooterHolder(ViewGroup parent) {
        super(R.layout.adapter_list_footer, parent);
    }

    @Override
    protected void setState(int type) {
        switch (type) {
            case BaseFooterHolder.TYPE_RETRY:
                ((TextView) getView(R.id.more_loading_text)).setText("加载失败,点击重试");
                getView(R.id.more_loading_progressbar).setVisibility(View.GONE);
                break;
            case BaseFooterHolder.TYPE_LOADING:
                ((TextView) getView(R.id.more_loading_text)).setText("正在载入...");
                getView(R.id.more_loading_progressbar).setVisibility(View.VISIBLE);
                break;
            case BaseFooterHolder.TYPE_SHOW_NO_MORE_VIEW:
                ((TextView) getView(R.id.more_loading_text)).setText("已无更多数据");
                getView(R.id.more_loading_progressbar).setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
