package com.liujiakuo.boss.biz.position;


import android.os.Build;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.liujiakuo.boss.base.activity.SingleFragmentHelper;
import com.liujiakuo.boss.base.fragment.BaseListFragment;
import com.liujiakuo.boss.base.http.RequestDefine;
import com.liujiakuo.boss.base.http.response.PageDataResponse;
import com.liujiakuo.boss.base.list.BasePageListAdapter;
import com.liujiakuo.boss.base.list.BaseViewHolder;
import com.liujiakuo.boss.holder.PositionHolder;
import com.liujiakuo.boss.utils.JsonUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.IParseNetwork;

import java.util.List;

import okhttp3.Request;

/**
 * liujiakuo on 2019/2/18 09:29
 * 职位列表
 */
public class PositionListFragment extends BaseListFragment<PositionBean, PageDataResponse<List<PositionBean>>, Void> {
    private int page = 0;

    @Override
    protected BasePageListAdapter<PositionBean, Void> createAdapter() {
        return new PositionAdapter();
    }

    /**
     * 构建获取职位列表的请求
     */
    @Override
    public CommonRequest<PageDataResponse<List<PositionBean>>> createRequest(boolean isRefresh) {
        Request jobListRequest = RequestDefine.getJobListRequest(this, page, isRefresh);
        CommonRequest<PageDataResponse<List<PositionBean>>> request = new CommonRequest<>(jobListRequest, new IParseNetwork<PageDataResponse<List<PositionBean>>>() {
            @Override
            public PageDataResponse<List<PositionBean>> parseNetworkResponse(String jsonStr) {
                return JsonUtils.fromJson(jsonStr, new TypeToken<PageDataResponse<List<PositionBean>>>() {
                });
            }
        });
        return request;
    }

    /**
     * 是否还有更多数据
     */
    @Override
    protected boolean checkHasMore(PageDataResponse<List<PositionBean>> response) {
        return response != null && response.getData() != null && !response.getData().isEmpty();
    }

    @Override
    protected void updateAdapterData(BasePageListAdapter<PositionBean, Void> adapter, PageDataResponse<List<PositionBean>> response, boolean isRefresh, boolean isNetResponse) {
        if (response == null || response.getData() == null) {
            return;
        }
        page = response.getPage();
        if (adapter != null) {
            adapter.updateDataNotifyItem(response.getData(), isRefresh);
        }
    }

    @Override
    protected void onItemClick(BaseViewHolder holder) {
        //item点击跳转
        PositionHolder positionHolder = null;
        if(holder instanceof PositionHolder){
            positionHolder = (PositionHolder) holder;
        }else {
            return;
        }
        PositionBean itemData = positionHolder.getItemData();
        Bundle bundle = new Bundle();
        bundle.putString(PositionDefaultFragment.POSITION_ID,itemData.getPid());
        SingleFragmentHelper.startSingleFragment(getContext(),PositionDefaultFragment.class.getName(),
                PositionDefaultFragment.class.getSimpleName(),bundle);
    }
}
