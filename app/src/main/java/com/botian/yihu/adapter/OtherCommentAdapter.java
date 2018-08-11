package com.botian.yihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.util.ACache;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private int iscai = 1;//1点赞，2取消点赞


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userface)
        CircleImageView userface;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image_praise)
        ImageView imagePraise;
        @BindView(R.id.commentList_item_tv_praise)
        TextView commentListItemTvPraise;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public OtherCommentAdapter(Context mContext, RxAppCompatActivity yy, List<OtherCommentBean.DataBeanX.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        this.yy = yy;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_comment, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件 点击切换fragment
        myViewHolder.imagePraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                final int position = myViewHolder.getAdapterPosition() - 2;
                ObserverOnNextListener<ZanBean> listener = new ObserverOnNextListener<ZanBean>() {
                    @Override
                    public void onNext(ZanBean data1) {
                        String a = data1.getMsg();
                        if (data1.getCode() == 400) {
                            Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();
                        } else {
                            if (iscai == 1) {
                                //TextView textView = view.findViewById(R.id.commentList_item_tv_praise);
                                //ImageView imaageView = view.findViewById(R.id.image_praise);
                                myViewHolder.imagePraise.setImageResource(R.drawable.detail_like_p);

                                String str = data.get(position).getCai_num() + 1 + "";
                                myViewHolder.commentListItemTvPraise.setText(str);

                                //textView.setText(str);
                                myViewHolder.commentListItemTvPraise.setTextColor(mContext.getResources().getColor(R.color.blue));

                                iscai = 2;
                            } else {

                                //ImageView imaageView = view.findViewById(R.id.image_praise);
                                //TextView textView = view.findViewById(R.id.commentList_item_tv_praise);
                                String str = data.get(position).getCai_num() + "";
                                myViewHolder.imagePraise.setImageResource(R.drawable.detail_like);
                                myViewHolder.commentListItemTvPraise.setText(str);
                                myViewHolder.commentListItemTvPraise.setTextColor(mContext.getResources().getColor(R.color.default_text));

                                iscai = 1;
                            }
                        }
                    }
                };
                mCache = ACache.get(mContext);
                //从缓存读取用户信息
                userInfo = (UserInfo) mCache.getAsObject("userInfo");
                if (userInfo == null) {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    String userid = userInfo.getId() + "";
                    String mid = data.get(position).getId() + "";
                    ApiMethods.getCommentZan(new MyObserver<ZanBean>(listener), mid, userid, iscai + "", yy);
                    //notifyDataSetChanged();//刷新
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = data.get(position).getUsres().getUsername();
        holder.name.setText(name);
        String date = data.get(position).getCreate_time().substring(2, 16);
        holder.date.setText(date);
        String content = data.get(position).getContent();
        holder.content.setText(content);
        String commentListItemTvPraise = data.get(position).getCai_num() + "";
        holder.commentListItemTvPraise.setText(commentListItemTvPraise);
        String picUrl = (String) data.get(position).getUsres().getAvatar();
        if (picUrl != null && !picUrl.equals("")) {
            Glide.with(mContext)
                    .load("http://btsc.botian120.com" + picUrl)
                    .into(holder.userface);
        } else {
            holder.userface.setImageResource(R.drawable.home_head_default);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

