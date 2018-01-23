package com.example.joshuayingwhat.pigsyloan.widget;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView的条目点击事件
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class ItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private GestureDetectorCompat gestureDetector;
    private onItemClickListener clickListener;

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public ItemClickListener(final RecyclerView recyclerView, onItemClickListener listener) {
        this.clickListener = listener;
        gestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && clickListener != null) {
                    clickListener.onItemClick(childView, recyclerView.getChildLayoutPosition(childView));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }
}
