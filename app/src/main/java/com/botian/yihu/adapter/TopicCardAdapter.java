package com.botian.yihu.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.eventbus.TopicCardEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2018/2/23 0023.
 */
public class TopicCardAdapter extends RecyclerView.Adapter<TopicCardAdapter.MyViewHolder> {
    private ArrayList<Integer> data;
    private Context mContext;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView textView;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public TopicCardAdapter(Context mContext, ArrayList<Integer>  data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_card, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 1;
                EventBus.getDefault().post(new TopicCardEvent(position));
                Activity activity = (Activity)mContext;
                activity.finish();
                //notifyDataSetChanged();//刷新
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String a=position+1+"";
        holder.textView.setText(a);
        if (data.get(position)==0){
            holder.textView.setBackgroundResource(R.drawable.topic_cord_correct);
        }else if(data.get(position)==1){
            holder.textView.setBackgroundResource(R.drawable.topic_cord_false);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

