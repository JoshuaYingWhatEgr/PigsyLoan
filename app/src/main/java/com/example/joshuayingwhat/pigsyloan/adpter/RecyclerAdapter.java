package com.example.joshuayingwhat.pigsyloan.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joshuayingwhat.pigsyloan.R;

import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public Context context;
    public List<String> mListString;
    public List<Integer> mListImage;

    public RecyclerAdapter(Context context, List<Integer> mListImage, List<String> mListString) {
        this.mListImage = mListImage;
        this.mListString = mListString;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder mViewHodler = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.hot_product_layout, parent, false));
        return mViewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImageIcon.setImageResource(mListImage.get(position));
        holder.mTextTitle.setText(mListString.get(position));
    }

    @Override
    public int getItemCount() {
        return mListString.size();
    }

    public void addAll(int position, List<String> mItemData, List<Integer> mListInt) {
        mListString.addAll(mItemData);
        mListImage.addAll(mListInt);
        //从这里刷新
        notifyItemRangeChanged(position, mItemData.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextTitle;
        public ImageView mImageIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageIcon = (ImageView) itemView.findViewById(R.id.iv_icon_product);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_product);
        }
    }
}
