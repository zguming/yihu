package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.NewsContentActivity;
import com.botian.yihu.beans.NewsList;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {
    private List<NewsList.DataBeanX.DataBean> data;
    private Context mContext;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_title)
        TextView newsTitle;
        @BindView(R.id.news_pic)
        ImageView newsPic;
        @BindView(R.id.news_src)
        TextView newsSrc;
        @BindView(R.id.news_time)
        TextView newsTime;
        @BindView(R.id.top_line)
        View topLine;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public NewsListAdapter(Context mContext, List<NewsList.DataBeanX.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件 点击切换fragment
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                final int position = myViewHolder.getAdapterPosition() - 2;
                Intent intent = new Intent(mContext, NewsContentActivity.class);
                intent.putExtra("id", data.get(position).getId() + "");
                intent.putExtra("content", data.get(position).getcontent());
                mContext.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = data.get(position).getTitle();
        holder.newsTitle.setText(title);
        String date = data.get(position).getCreate_time().substring(2, 10);
        holder.newsSrc.setText(date);
        String aa = data.get(position).getClick() + "次阅读";
        holder.newsTime.setText(aa);
        if (position==0){
            holder.topLine.setVisibility(View.VISIBLE);
        }
        if (data.get(position).getLitpic() != null && !data.get(position).getLitpic().equals("")) {
            holder.newsPic.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load("http://btsc.botian120.com" + data.get(position).getLitpic())
                    //.centerCrop()
                    .into(holder.newsPic);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}