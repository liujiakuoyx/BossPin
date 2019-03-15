package com.liujiakuo.boss.base.list;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * liujiakuo on 2019/2/20 16:39
 */
public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;

    public RecyclerItemClickListener(final RecyclerView recyclerView) {
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        if (recyclerView == null) {
                            return false;
                        }
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(childView);
                        if (viewHolder instanceof BaseViewHolder) {
                            onItemClick((BaseViewHolder) viewHolder);
                        }

                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(childView);
                        if (viewHolder instanceof BaseViewHolder) {
                            onItemLongClick((BaseViewHolder) viewHolder);
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    public void onItemClick(BaseViewHolder holder) {

    }

    public void onItemLongClick(BaseViewHolder holder) {

    }
}
