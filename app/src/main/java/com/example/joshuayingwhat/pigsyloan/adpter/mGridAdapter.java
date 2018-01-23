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
public class mGridAdapter extends RecyclerView.Adapter<mGridAdapter.MyViewHolder> {

    private List<String> mString;
    private List<Integer> mInteger;
    private Context context;

    public mGridAdapter(Context context, List<Integer> mInteger, List<String> mString) {
        this.context = context;
        this.mInteger = mInteger;
        this.mString = mString;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder mHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(mInteger.get(position));
        holder.mTextView.setText(mString.get(position));
    }

    @Override
    public int getItemCount() {
        return mInteger.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //这个是图标
            mImageView = (ImageView) itemView.findViewById(R.id.iv_loanmode);
            mTextView = (TextView) itemView.findViewById(R.id.tv_loanmode);
        }
    }
}
