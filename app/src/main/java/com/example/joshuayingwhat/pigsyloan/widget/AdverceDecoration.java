package com.example.joshuayingwhat.pigsyloan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置RecyclerView的分割线
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class AdverceDecoration extends RecyclerView.ItemDecoration {
    //采用系统风格的分割线
    private static final int attr[] = {android.R.attr.listDivider};
    private Drawable mDrawable;
    private View childView;

    public AdverceDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(attr);
        mDrawable = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //水平方向的分割线
        //左边缘
        int left = parent.getLeft();
        //右边缘
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            childView = parent.getChildAt(i);
            RecyclerView.LayoutParams mRecyclerLayout = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int top = childView.getBottom() + mRecyclerLayout.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight() + 10;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);


        }
    }
}
