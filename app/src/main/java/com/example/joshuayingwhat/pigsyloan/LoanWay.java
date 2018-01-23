package com.example.joshuayingwhat.pigsyloan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.joshuayingwhat.pigsyloan.adpter.RecyclerAdapter;
import com.example.joshuayingwhat.pigsyloan.loan.LoanPresenter;
import com.example.joshuayingwhat.pigsyloan.widget.AdverceDecoration;
import com.example.joshuayingwhat.pigsyloan.widget.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class LoanWay extends AppCompatActivity {

    public List<Integer> recyclerWay;
    public List<String> recyclerText;
    private RecyclerView mRecyclerView;
    private MaterialRefreshLayout mMaterialRefreshLayout;
    public RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanway_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_loan_way);
        mMaterialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.material_refresh_layout);
        int[] mRecyclerImage = new int[]{R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4, R.mipmap.icon5,
                R.mipmap.icon6, R.mipmap.icon7, R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4, R.mipmap.icon5, R.mipmap.icon6, R.mipmap.icon7, R.mipmap.icon1};
        recyclerWay = new ArrayList<>();
        for (int i = 0; i < mRecyclerImage.length; i++) {
            recyclerWay.add(mRecyclerImage[i]);
        }
        String[] mRecyclerText = new String[]{"51图标零用钱-51零用钱", "零用钱现金在线-现金在线", "贷上钱-贷上钱", "信用白条-信用白条"
                , "钱站-现金贷", "现金贷-现金贷", "杏仁钱包-杏仁钱包", "51图标零用钱-51零用钱", "零用钱现金在线-现金在线", "贷上钱-贷上钱",
                "贷上钱-贷上钱", "信用白条-信用白条", "现金现金侠-现金贷", "杏仁钱包-杏仁钱包", "51图标零用钱-51零用钱"};
        recyclerText = new ArrayList<>();
        for (int i = 0; i < mRecyclerText.length; i++) {
            recyclerText.add(mRecyclerText[i]);
        }

        //设置布局样式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new AdverceDecoration(this));
        recyclerAdapter = new RecyclerAdapter(this, recyclerWay, recyclerText);
        mRecyclerView.setAdapter(recyclerAdapter);
        //设置下拉加载和上拉刷新
        mMaterialRefreshLayout.setLoadMore(true);
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.finishRefresh();
            }

            //设置上拉加载
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.addAll(recyclerAdapter.getItemCount(), getStringListData(), getIntegerListData());
                        mMaterialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 2000);
            }
        });

        //recyclerView条目点击事件
        mRecyclerView.addOnItemTouchListener(new ItemClickListener(mRecyclerView, new ItemClickListener.onItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(App.getINSTANCE(), ProductDisplayActivity.class));
            }
        }));
    }

    //recyclerview数据
    public List<String> getStringListData() {
        List<String> mListString = new ArrayList<>();
        String[] mTitle = new String[]{"极速钱包-极速钱包", "现金借款-现金借款", "发薪贷-发薪贷"};
        for (int i = 0; i < mTitle.length; i++) {
            mListString.add(mTitle[i]);
        }

        return mListString;
    }

    public List<Integer> getIntegerListData() {
        List<Integer> mListInteger = new ArrayList<>();
        int[] imagepic = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        for (int i = 0; i < imagepic.length; i++) {
            mListInteger.add(imagepic[i]);
        }
        return mListInteger;
    }
}
