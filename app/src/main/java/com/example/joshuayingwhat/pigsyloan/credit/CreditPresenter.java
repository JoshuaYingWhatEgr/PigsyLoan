package com.example.joshuayingwhat.pigsyloan.credit;

import com.example.joshuayingwhat.pigsyloan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class CreditPresenter implements CreditConstroct.presenter {

    public CreditConstroct.view mView;
    public List<Integer> mInteger;
    public List<String> mListTitle;

    public CreditPresenter(CreditConstroct.view mView) {
        this.mView = mView;
    }

    @Override
    public void getRecyclerData() {
        //处理RecyclerView数据
        mInteger = new ArrayList<>();
        int[] images = new int[]{R.mipmap.bank1, R.mipmap.bank2,R.mipmap.bank3
                , R.mipmap.bank4, R.mipmap.bank5, R.mipmap.bank6,R.mipmap.bank7
        };
        for (int i = 0; i < images.length; i++) {
            mInteger.add(images[i]);
        }

        mListTitle = new ArrayList<>();
        String[] mTitle = new String[]{"VISA全球支付信用卡(海淘版)", "上海银行银联信用卡(精致版)", "光大阳光信用卡",

                "光大淘票票公仔联名卡", "爱奇艺联名信用卡金卡", "上海银行淘宝白金卡", "光大菁英信用卡"};
        for (int i = 0; i < mTitle.length; i++) {
            mListTitle.add(mTitle[i]);
        }
        //将处理过后的数据传递到View层，进行显示
        mView.setRecyclerData(mInteger, mListTitle);
    }
}
