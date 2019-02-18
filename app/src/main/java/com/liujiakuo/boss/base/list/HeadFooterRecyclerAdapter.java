package com.liujiakuo.boss.base.list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.liujiakuo.boss.holder.RecyclerViewItemType;

/**
 * Created by 佳阔 on 2019/2/17.
 * 带头部尾部的adapter
 *
 * @param <T>  item数据类型
 * @param <HD> 头部数据类型
 * @param <FD> 尾部数据类型
 */
public abstract class HeadFooterRecyclerAdapter<T, HD, FD> extends BaseRecyclerViewAdapter<T, BaseViewHolder<T>> {
    private HD mHeadHolderData;
    private FD mFooterHolderData;
    private BaseViewHolder<HD> mHeadHolder;
    private BaseViewHolder<FD> mFooterHolder;
    private OnItemBindListener mItemBindListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerViewItemType.HEAD) {
            if (mHeadHolder == null) {
                mHeadHolder = createHeadViewHolder(parent, viewType);
            }
            return mHeadHolder;
        } else if (viewType == RecyclerViewItemType.FOOTER) {
            mFooterHolder = createFooterViewHolder(parent, viewType);
            return mFooterHolder;
        } else {
            BaseViewHolder viewHolder = createBasicViewHolder(parent, viewType);
            return viewHolder;
        }
    }

    //创建item
    protected abstract BaseViewHolder createBasicViewHolder(ViewGroup parent, int viewType);

    //创建尾部
    protected abstract BaseViewHolder<FD> createFooterViewHolder(ViewGroup parent, int viewType);

    //创建头部
    protected abstract BaseViewHolder<HD> createHeadViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        if (holder == null) {
            return;
        }
        int itemViewType = holder.getItemViewType();
        if (itemViewType == RecyclerViewItemType.HEAD) {
            //bind head view
            if (mHeadHolder != null) {
                mHeadHolder.bindView(mHeadHolderData);
            }
            if (mItemBindListener != null) {
                mItemBindListener.bindHead(holder);
            }
        } else if (itemViewType == RecyclerViewItemType.FOOTER) {
            //bind footer holder
            if (mFooterHolder != null) {
                mFooterHolder.bindView(mFooterHolderData);
            }
            if (mItemBindListener != null) {
                mItemBindListener.bindFooter(holder);
            }
        } else {
            //拿到item绝对位置(除去头部)
            int basicPosition = getBasicPosition(position);
            holder.bindView(getBasicItem(basicPosition), basicPosition);
            if (mItemBindListener != null) {
                mItemBindListener.bindBasicItem(holder, getBasicPosition(position));
            }
        }

    }

    public FD getFooterData() {
        return mFooterHolderData;
    }

    public HD getHeadData() {
        return mHeadHolderData;
    }

    /**
     * 添加头部
     */
    public void setHeadData(HD data) {
        boolean origShwo = showHeader();
        mHeadHolderData = data;
        if (mHeadHolderData != null) {
            mHeadHolder.bindView(mHeadHolderData);
        }
        if (origShwo != showHeader()) {
            //头部添加或者移除需要更新列表数据
            notifyDataSetChanged();
        }
    }

    public void setOnItemBindListener(@NonNull OnItemBindListener listener) {
        mItemBindListener = listener;
    }

    /**
     * 添加尾部
     */
    public void setFooterData(FD data) {
        boolean origShwo = showFooter();
        int itemCount = getItemCount();
        mFooterHolderData = data;
        if (origShwo) {
            //之前是显示的
            if (showFooter()) {
                //更新
                notifyItemChanged(itemCount - 1);
            } else {
                //移除
                notifyItemRemoved(itemCount - 1);
            }
        } else {
            //插入新的尾部
            notifyItemInserted(itemCount);
        }
    }

    /**
     * 获取显示头部时的item的position
     */
    public int getBasicPosition(int position) {
        if (showHeader()) {
            return position - 1;
        }
        return position;
    }

    /**
     * 获取添加了头部尾部的列表大小
     */
    @Override
    public int getItemCount() {
        int itemCount = getBasicItemCount();
        if (showHeader()) {
            itemCount += 1;
        }
        if (showFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    //是否显示头部
    private boolean showHeader() {
        return mHeadHolderData != null;
    }

    //是否显示尾部
    private boolean showFooter() {
        return mFooterHolderData != null;
    }

    /**
     * 拿到第basicPos个列表数据
     */
    public T getBasicItem(int basicPos) {
        return super.getItem(basicPos);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && showHeader()) {
            return RecyclerViewItemType.HEAD;
        }
        if (position == getItemCount() - 1 && showFooter()) {
            return RecyclerViewItemType.FOOTER;
        }
        T basicItem = getBasicItem(getBasicPosition(position));
        return getBasicItemType(basicItem);
    }

    /**
     * 通过数据，得到对应的itemType
     * 需要子类继承
     */
    public abstract int getBasicItemType(T basicItem);

    public interface OnItemBindListener<HD, T, FD> {
        void bindHead(BaseViewHolder<HD> holder);

        void bindBasicItem(BaseViewHolder<T> holder, int position);

        void bindFooter(BaseViewHolder<FD> holder);
    }
}
