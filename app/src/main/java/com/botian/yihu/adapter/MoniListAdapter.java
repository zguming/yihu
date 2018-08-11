package com.botian.yihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.beans.MoniTest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class MoniListAdapter extends RecyclerView.Adapter<MoniListAdapter.MyViewHolder> {

    private List<MoniTest.DataBean> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.name)
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public MoniListAdapter(Context mContext, List<MoniTest.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moni_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(data.get(position).getTypename());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
