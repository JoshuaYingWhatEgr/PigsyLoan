package com.example.joshuayingwhat.pigsyloan.loan;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.joshuayingwhat.pigsyloan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class LoanPresenter implements LoanConstroct.Presenter {

    public LoanConstroct.View mView;

    public List<Integer> imageViews;

    public List<Integer> gridImage;

    public List<String> gridText;

    private List<Integer> recyclerImages;

    private List<String> recyclerText;

    //所有需要轮播的图片数据
    public int[] mImageId = new int[]{R.mipmap.page1, R.mipmap.page2, R.mipmap.page3, R.mipmap.page4, R.mipmap.page5};

    //GridView布局中的数据参数
    public int[] mGridImage = new int[]{R.mipmap.loan_product, R.mipmap.loan_information, R.mipmap.loan_notice,

            R.mipmap.loan_record, R.mipmap.loan_help, R.mipmap.loan_inventory};

    public String[] mGridString = new String[]{"借款产品", "借款信息", "借贷注意事项", "借贷记录", "借贷帮助", "借款清单"};

    //RecyclerViewecyclerView图标
    public int[] mRecyclerImage = new int[]{R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4, R.mipmap.icon5,
            R.mipmap.icon6, R.mipmap.icon7};
    public String[] mRecyclerText = new String[]{"51图标零用钱-51零用钱", "零用钱现金在线-现金在线", "贷上钱-贷上钱", "信用白条-信用白条"
            , "钱站-现金贷", "现金现金侠-现金贷", "杏仁钱包-杏仁钱包"};

    public LoanPresenter(LoanConstroct.View view) {
        mView = view;
    }

    //在这里获取图片资源并处理图片轮播逻辑
    @Override
    public void getPhotograph() {
        //添加图片的容器imageViews
        imageViews = new ArrayList<>();
        for (int i = 0; i < mImageId.length; i++) {
            imageViews.add(mImageId[i]);
        }
        mView.setPhotograph(imageViews);
        //添加图标和文字到gridview中
        gridImage = new ArrayList<>();
        for (int i = 0; i < mGridImage.length; i++) {
            gridImage.add(mGridImage[i]);
        }

        gridText = new ArrayList<>();
        for (int i = 0; i < mGridString.length; i++) {
            gridText.add(mGridString[i]);
        }

        mView.setGridViewImage(gridImage, gridText);

        //添加钱包图标信息到RecyclerView
        recyclerImages = new ArrayList<>();
        for (int i = 0; i < mRecyclerImage.length; i++) {
            recyclerImages.add(mRecyclerImage[i]);
        }
        //图标添加文字信息到RecyclerView
        recyclerText = new ArrayList<>();
        for (int i = 0; i < mRecyclerText.length; i++) {
            recyclerText.add(mRecyclerText[i]);
        }
        mView.setRecyclerView(recyclerImages, recyclerText);
    }

    @Override
    public void setPagerdelay(Handler mHandler) {
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    //滚动操作
    @Override
    public void setRollOperation(ViewPager mloanPager, final Handler mHandler) {
        int currentItem = mloanPager.getCurrentItem();
        mloanPager.setCurrentItem(++currentItem);
        setPagerdelay(mHandler);
        //获取用户按下手指时的操作
        mloanPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                }
                return false;
            }
        });
    }
}
