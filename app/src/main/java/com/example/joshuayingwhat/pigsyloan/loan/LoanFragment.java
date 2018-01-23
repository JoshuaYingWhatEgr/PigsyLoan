package com.example.joshuayingwhat.pigsyloan.loan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.joshuayingwhat.pigsyloan.App;
import com.example.joshuayingwhat.pigsyloan.LoanWay;
import com.example.joshuayingwhat.pigsyloan.ProductDisplayActivity;
import com.example.joshuayingwhat.pigsyloan.R;
import com.example.joshuayingwhat.pigsyloan.adpter.LoanAdapter;
import com.example.joshuayingwhat.pigsyloan.adpter.LoanGridAdapter;
import com.example.joshuayingwhat.pigsyloan.adpter.RecyclerAdapter;
import com.example.joshuayingwhat.pigsyloan.webview.WebViewActivity;
import com.example.joshuayingwhat.pigsyloan.widget.AdverceDecoration;
import com.example.joshuayingwhat.pigsyloan.widget.CustomGridView;
import com.example.joshuayingwhat.pigsyloan.widget.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/6.
 */
public class LoanFragment extends Fragment implements LoanConstroct.View {

    public ViewPager mloanPager;
    public LoanConstroct.Presenter loanPresenter = new LoanPresenter(this);
    public GridView mGridView;
    public RecyclerView mRecyclerView;
    public MaterialRefreshLayout mMaterialRefreshLayout;
    private RecyclerAdapter recyclerAdapter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loanPresenter.setRollOperation(mloanPager, mHandler);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loan_layout, container, false);
        mloanPager = (ViewPager) view.findViewById(R.id.loan_ad);
        mGridView = (GridView) view.findViewById(R.id.loan_mode);
        mMaterialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_hot_product);


        loanPresenter.getPhotograph();
        loanPresenter.setPagerdelay(mHandler);
        //设置第一个广告轮播页面
        mloanPager.setCurrentItem(0);
        //设置上拉刷新下拉加载
        mMaterialRefreshLayout.setLoadMore(true);
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //结束下拉刷新
                materialRefreshLayout.finishRefresh();
            }

            @Override
            //上拉加载
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                //执行上拉加载操作
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.addAll(recyclerAdapter.getItemCount(), getStringListData(), getIntegerListData());
                        mMaterialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 2000);
            }
        });

        //点击Recyclerview的item获取产品详情
        mRecyclerView.addOnItemTouchListener(new ItemClickListener(mRecyclerView, new ItemClickListener.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(App.getINSTANCE(), ProductDisplayActivity.class));
            }
        }));
        return view;
    }

    //设置轮播图布局
    @Override
    public void setPhotograph(List<Integer> mListImage) {
        mloanPager.setAdapter(new LoanAdapter(App.getINSTANCE(), mListImage));
    }

    //设置GridView布局
    @Override
    public void setGridViewImage(final List<Integer> mListImage, List<String> mListString) {
//        mRecyclerView.setAdapter(new mGridAdapter(App.getINSTANCE(), mListImage, mListString));
        mGridView.setAdapter(new LoanGridAdapter(App.getINSTANCE(), mListImage, mListString));
        //设置Gridview监听
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (mListImage.get(i)) {
                    case R.mipmap.loan_help:
                    case R.mipmap.loan_information:
                    case R.mipmap.loan_inventory:
                    case R.mipmap.loan_notice:
                    case R.mipmap.loan_product:
                    case R.mipmap.loan_record:
                    default:
                        startActivity(new Intent(App.getINSTANCE(), LoanWay.class));
                        break;
                }
            }
        });
    }

    //设置RecyclerView的Adapter
    @Override
    public void setRecyclerView(List<Integer> mListImage, List<String> mListString) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        recyclerAdapter = new RecyclerAdapter(App.getINSTANCE(), mListImage, mListString);
        mRecyclerView.setAdapter(recyclerAdapter);
        //设置RecyclerView的分割线
        mRecyclerView.addItemDecoration(new AdverceDecoration(App.getINSTANCE()));
    }

    //跳转到webview页面
    @Override
    public void setSkipWebView() {
        startActivity(new Intent(App.getINSTANCE(), WebViewActivity.class));
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
