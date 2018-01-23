package com.example.joshuayingwhat.pigsyloan.loan;

import android.os.Handler;
import android.support.v4.view.ViewPager;

import com.example.joshuayingwhat.pigsyloan.base.BasePresenter;
import com.example.joshuayingwhat.pigsyloan.base.BaseView;

import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class LoanConstroct {
    interface View extends BaseView {
        /**
         * 从Presenter层中获取到的图片集合将其显示到界面中
         *
         * @param mListImage 图片集合
         */
        void setPhotograph(List<Integer> mListImage);

        /**
         * 设置GridView
         */
        void setGridViewImage(List<Integer> mListImage, List<String> mListString);

        /**
         * 设置RecyclerView
         */
        void setRecyclerView(List<Integer> mListImage, List<String> mListString);

        //设置跳转到WebView页面
        void setSkipWebView();
    }

    interface Presenter extends BasePresenter {
        /**
         * 从资源文件中获取到的图片
         */
        void getPhotograph();

        //设置图片延时
        void setPagerdelay(Handler mHandler);

        //设置轮播图滚动操作
        void setRollOperation(ViewPager pager, Handler mHandler);
    }
}
