package com.example.joshuayingwhat.pigsyloan.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class LoanAdapter extends PagerAdapter {

    public Context context;
    public List<Integer> mImages;
    public ImageView mImageView;

    public LoanAdapter(Context context, List<Integer> mImages) {
        this.context = context;
        this.mImages = mImages;
    }

    //因为是无线轮播所以需要传入一个较大的值
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化轮播内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //为图片设置下标
        int pos = position % mImages.size();
        mImageView = new ImageView(context);
        mImageView.setBackgroundResource(mImages.get(pos));
        container.addView(mImageView);
        return mImageView;
    }

    //移除不在轮播范围的图片
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
