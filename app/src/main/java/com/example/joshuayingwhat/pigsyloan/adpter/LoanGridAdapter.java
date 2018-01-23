package com.example.joshuayingwhat.pigsyloan.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joshuayingwhat.pigsyloan.R;

import java.util.List;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class LoanGridAdapter extends BaseAdapter {

    private List<String> mListString;
    private Context context;
    private List<Integer> mListImage;

    public LoanGridAdapter(Context context, List<Integer> mListImage, List<String> mListString) {
        this.context = context;
        this.mListImage = mListImage;
        this.mListString = mListString;
    }

    @Override
    public int getCount() {
        return mListImage.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item_layout, viewGroup, false);
            mViewHolder = new ViewHolder();
            mViewHolder.mIconImage = (ImageView) view.findViewById(R.id.iv_loanmode);
            mViewHolder.mTextView = (TextView) view.findViewById(R.id.tv_loanmode);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        mViewHolder.mIconImage.setImageResource(mListImage.get(i));
        mViewHolder.mTextView.setText(mListString.get(i));
        return view;
    }

    class ViewHolder {
        ImageView mIconImage;
        TextView mTextView;
    }
}
