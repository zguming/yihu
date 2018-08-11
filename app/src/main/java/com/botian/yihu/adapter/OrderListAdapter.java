package com.botian.yihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.beans.PayOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {


    private List<PayOrder.DataBean> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_no)
        TextView tvOrderNo;
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.tv_order_body)
        TextView tvOrderBody;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public OrderListAdapter(Context mContext, List<PayOrder.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_listitem, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String orderNo="订单号："+data.get(position).getOrder();
        String money="为账户充值 ￥"+data.get(position).getMoney()+"元";
        String time="时间："+data.get(position).getCreate_time();
        holder.tvOrderNo.setText(orderNo);
        holder.tvOrderBody.setText(money);
        holder.tvOrderTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}