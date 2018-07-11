package com.botian.yihu.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.GetNewsComment;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.util.ACache;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class NewsCommentAdapter extends RecyclerView.Adapter<NewsCommentAdapter.MyViewHolder> {
    private List<GetNewsComment.DataBeanX.DataBean> data;
    private ACache mCache;
    private UserInfo userInfo;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userface)
        CircleImageView userface;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.commentList_item_tv_praise)
        TextView commentListItemTvPraise;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public NewsCommentAdapter(Context mContext, List<GetNewsComment.DataBeanX.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_comment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Drawable mPraise = mContext.getResources().getDrawable(R.drawable.detail_like);
        //mPraise.setBounds(0, 0, 40, 40);
        //holder.commentListItemTvPraise.setCompoundDrawables(mPraise, null, null, null);
        holder.commentListItemTvPraise.setVisibility(View.GONE);

        String name = data.get(position).getUsres().getUsername();
        holder.name.setText(name);
        String date = data.get(position).getCreate_time().substring(2, 16);
        holder.date.setText(date);
        String content = data.get(position).getContent();
        holder.content.setText(content);
        String commentListItemTvPraise = data.get(position).getCai_num() + "";
        holder.commentListItemTvPraise.setText(commentListItemTvPraise);
        String picUrl=(String)data.get(position).getUsres().getAvatar();
        if (picUrl!=null&& !picUrl.equals("")) {
            Glide.with(mContext)
                    .load("http://btsc.botian120.com"+picUrl)
                    .into(holder.userface);
        }else {
            holder.userface.setImageResource(R.drawable.home_head_default);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

