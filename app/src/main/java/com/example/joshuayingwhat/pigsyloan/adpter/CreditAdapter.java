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
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {
    private List<String> mTitle;
    private List<Integer> mImageIcon;
    private Context context;

    public CreditAdapter(Context context, List<Integer> mImageIcon, List<String> mTitle) {
        this.context = context;
        this.mImageIcon = mImageIcon;
        this.mTitle = mTitle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder mViewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.hot_credit_layout, parent, false));
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mHotCreditImage.setImageResource(mImageIcon.get(position));
        holder.mHotCreditTv.setText(mTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageIcon.size();
    }

    public void addAll(int position, List<Integer> mInteger, List<String> mString) {
        mImageIcon.addAll(mInteger);
        mTitle.addAll(mString);
        notifyItemRangeChanged(position, mInteger.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mHotCreditImage;
        private TextView mHotCreditTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mHotCreditTv = (TextView) itemView.findViewById(R.id.hot_credit_tv);
            mHotCreditImage = (ImageView) itemView.findViewById(R.id.hot_credit_image);
        }
    }
}
