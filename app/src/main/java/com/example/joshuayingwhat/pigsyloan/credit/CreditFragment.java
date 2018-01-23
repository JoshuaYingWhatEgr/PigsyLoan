package com.example.joshuayingwhat.pigsyloan.credit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.joshuayingwhat.pigsyloan.App;
import com.example.joshuayingwhat.pigsyloan.R;
import com.example.joshuayingwhat.pigsyloan.adpter.CreditAdapter;
import com.example.joshuayingwhat.pigsyloan.webview.WebViewActivity;
import com.example.joshuayingwhat.pigsyloan.widget.AdverceDecoration;
import com.example.joshuayingwhat.pigsyloan.widget.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JoshuaYingWhat on 2017/7/6.
 */
public class CreditFragment extends Fragment implements CreditConstroct.view, View.OnClickListener {

    CreditConstroct.presenter presenter = new CreditPresenter(this);
    private RecyclerView mCreditRecycler;
    private MaterialRefreshLayout mCreditRefresh;
    private CreditAdapter recyclerAdapter;
    private CardView mCitic;
    private CardView mAttrict;
    private CardView mPeople;
    private CardView mInc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.credit_layout, container, false);
        mCreditRecycler = (RecyclerView) mView.findViewById(R.id.credit_recycler);
        mAttrict = (CardView) mView.findViewById(R.id.attrict_card);
        mPeople = (CardView) mView.findViewById(R.id.people_card);
        mCitic = (CardView) mView.findViewById(R.id.citic_card);
        mInc = (CardView) mView.findViewById(R.id.inc_card);
        mCreditRefresh = (MaterialRefreshLayout) mView.findViewById(R.id.credit_refresh);
        presenter.getRecyclerData();
        //添加RecyclerView item的监听操作
        mCreditRecycler.addOnItemTouchListener(new ItemClickListener(mCreditRecycler, new ItemClickListener.onItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //点击item跳转银行卡webview页面
                startActivity(new Intent(App.getINSTANCE(), WebViewActivity.class));
            }
        }));

        //设置Cardview监听
        mCitic.setOnClickListener(this);
        mPeople.setOnClickListener(this);
        mAttrict.setOnClickListener(this);
        mInc.setOnClickListener(this);
        return mView;
    }

    @Override
    public void setRecyclerData(List<Integer> mImages, List<String> mTitle) {
        //recyclerView布局
        mCreditRecycler.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        recyclerAdapter = new CreditAdapter(App.getINSTANCE(), mImages, mTitle);
        mCreditRecycler.setAdapter(recyclerAdapter);
        mCreditRecycler.addItemDecoration(new AdverceDecoration(App.getINSTANCE()));
        //设置上拉加载，下拉刷新
        mCreditRefresh.setLoadMore(true);
        mCreditRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                //执行上拉加载操作
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.addAll(recyclerAdapter.getItemCount(), getImageList(), getStringListData());
                        mCreditRefresh.finishRefreshLoadMore();
                    }
                }, 2000);
            }
        });
    }

    public List<String> getStringListData() {
        String[] title = new String[]{"光大绿色零碳信用卡", "光大招财猫主题信用卡", "光大梦幻西游信用卡金卡"};
        List<String> mListStr = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            mListStr.add(title[i]);
        }
        return mListStr;
    }

    public List<Integer> getImageList() {
        int[] images = new int[]{R.mipmap.bank3, R.mipmap.bank5, R.mipmap.bank6};
        List<Integer> mListIntpic = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            mListIntpic.add(images[i]);
        }
        return mListIntpic;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attrict_card:
            case R.id.citic_card:
            case R.id.inc_card:
            case R.id.people_card:
            default:
                startActivity(new Intent(App.getINSTANCE(), WebViewActivity.class));
                break;
        }
    }
}
