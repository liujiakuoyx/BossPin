package com.liujiakuo.boss.base.fragment;

import android.util.Log;

import com.liujiakuo.boss.base.BaseItemBean;
import com.liujiakuo.boss.base.list.HeadFooterRecyclerAdapter;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.boss.base.http.RequestDefine;
import com.liujiakuo.core.http.IParseNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佳阔 on 2019/2/17.
 * 测试
 */

public class TestFragment extends BaseListFragment<BaseItemBean, List<BaseItemBean>, Void> {
    private int page = 0;

    @Override
    protected HeadFooterRecyclerAdapter createAdapter() {
        return null;
    }

    @Override
    protected void onNetResponse(List<BaseItemBean> response, boolean refresh) {

    }

    @Override
    public CommonRequest<List<BaseItemBean>> createRequest(boolean isRefresh) {
        CommonRequest<List<BaseItemBean>> request = new CommonRequest<>(RequestDefine.getJobListRequest(this, page, isRefresh));
        request.setParseNetwork(new IParseNetwork<List<BaseItemBean>>() {
            @Override
            public List<BaseItemBean> parseNetworkResponse(String jsonStr) {
                Log.d("TAGTAG", "parseNetworkResponse: " + jsonStr);
                List<BaseItemBean> baseItemBeans = new ArrayList<>();
                baseItemBeans.add(new BaseItemBean());
                return baseItemBeans;
            }
        });
        return request;
    }

    @Override
    protected void updateAdapterData(HeadFooterRecyclerAdapter<BaseItemBean, Void, Integer> adapter, List<BaseItemBean> response, boolean isRefresh, boolean isNetResponse) {
        Log.d("TAGTAG", "updateAdapterData: " + response.size());
        adapter.updateDataNotifyItem(response, isRefresh);
    }
}
