package com.botian.yihu.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class OtherCommentAdapter extends RecyclerView.Adapter<OtherCommentAdapter.MyViewHolder> {
    private List<OtherCommentBean.DataBeanX.DataBean> data;
    private ACache mCache;
    private UserInfo userInfo;
    private Context mContext;
    private RxAppCompatActivity yy;
    private int iscai=1;//1点赞，0取消点赞
    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userface)
        ImageView userface;
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

    public OtherCommentAdapter(Context mContext,RxAppCompatActivity yy, List<OtherCommentBean.DataBeanX.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        this.yy = yy;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_comment, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件 点击切换fragment
        myViewHolder.commentListItemTvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                final int position = myViewHolder.getAdapterPosition()-2;
                ObserverOnNextListener<ZanBean> listener = new ObserverOnNextListener<ZanBean>() {
                    @Override
                    public void onNext(ZanBean data1) {
                        String a=data1.getMsg();
                        if(iscai==1){
                            TextView textView=view.findViewById(R.id.commentList_item_tv_praise);
                            Drawable mPraise = mContext.getResources().getDrawable(R.drawable.detail_like_p);
                            mPraise.setBounds(0, 0, 40, 40);
                            textView.setCompoundDrawables(mPraise, null, null, null);
                            String str=data.get(position).getCai_num()+1+"";
                            textView.setText(str);
                            textView.setTextColor(mContext.getResources().getColor(R.color.blue));

                            iscai=0;
                        }else{
                            TextView textView=view.findViewById(R.id.commentList_item_tv_praise);
                            Drawable mPraise = mContext.getResources().getDrawable(R.drawable.detail_like);
                            mPraise.setBounds(0, 0, 40, 40);
                            textView.setCompoundDrawables(mPraise, null, null, null);
                            String str=data.get(position).getCai_num()+"";
                            textView.setText(str);
                            textView.setTextColor(mContext.getResources().getColor(R.color.default_text));

                            iscai=1;
                        }
                    }
                };
                mCache= ACache.get(mContext);
                //从缓存读取用户信息
                userInfo = (UserInfo) mCache.getAsObject("userInfo");
                if(userInfo==null){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }else {
                    String userid = userInfo.getId() + "";
                    ApiMethods.getCommentZan(new MyObserver<ZanBean>(listener), data.get(position).getId() + "",userid,data.get(position).getMid()+"",iscai+"", yy);
                    //notifyDataSetChanged();//刷新
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Drawable mPraise = mContext.getResources().getDrawable(R.drawable.detail_like);
        mPraise.setBounds(0, 0, 40, 40);
        holder.commentListItemTvPraise.setCompoundDrawables(mPraise, null, null, null);
        String name = data.get(position).getUsres().getUsername();
        holder.name.setText(name);
        String date = data.get(position).getCreate_time();
        holder.date.setText(date);
        String content = data.get(position).getContent();
        holder.content.setText(content);
        String commentListItemTvPraise = data.get(position).getCai_num()+"";
        holder.commentListItemTvPraise.setText(commentListItemTvPraise);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

