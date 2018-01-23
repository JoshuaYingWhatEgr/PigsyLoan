package com.example.joshuayingwhat.pigsyloan.credit;

import com.example.joshuayingwhat.pigsyloan.base.BasePresenter;
import com.example.joshuayingwhat.pigsyloan.base.BaseView;

import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class CreditConstroct {
    interface view extends BaseView {
        //设置热门信用卡数据
        void setRecyclerData(List<Integer> mImages, List<String> mTitle);
    }

    interface presenter extends BasePresenter {
        void getRecyclerData();
    }
}
