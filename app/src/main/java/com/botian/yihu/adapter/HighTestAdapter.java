package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.HighTestAnswerActivity;
import com.botian.yihu.activity.MyCollectionPracticeActivity;
import com.botian.yihu.beans.HighTest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class HighTestAdapter extends RecyclerView.Adapter<HighTestAdapter.MyViewHolder> {
    private List<HighTest.DataBean> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.top_line)
        View topLine;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public HighTestAdapter(Context mContext, List<HighTest.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件 点击切换fragment
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 1;
                Intent intent = new Intent(mContext, HighTestAnswerActivity.class);
                //intent.putExtra("collectionRecordsParcel", collectionRecordsParcel);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
                //notifyDataSetChanged();//刷新
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String num = position + 1 + ".";
        //holder.num.setText(num);
        String name = num+data.get(position).getTitle();
        holder.title.setText(name);
        if (position == 0) {
            holder.topLine.setVisibility(View.VISIBLE);
        }
        String answer;
        switch (data.get(position).getCorrect()) {
            case "A":
                answer = data.get(position).getA();
                break;
            case "B":
                answer = data.get(position).getA();
                break;
            case "C":
                answer = data.get(position).getA();
                break;
            case "D":
                answer = data.get(position).getA();
                break;
            default:
                answer = data.get(position).getA();
                break;
        }
        String content = "答案: " + data.get(position).getCorrect() + "." + answer;
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

