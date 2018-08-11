package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.activity.MoniSubjectActivity;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.activity.VideoActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.util.ACache;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class VideoClassAdapter extends RecyclerView.Adapter<VideoClassAdapter.MyViewHolder> {
    private List<VideoClass.DataBean> data;
    private Context mContext;
    private ObserverOnNextListener<No> listener;
    private ACache mCache;
    private UserInfo userInfo;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.views)
        TextView views;
        @BindView(R.id.top_line)
        View topLine;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public VideoClassAdapter(Context mContext, List<VideoClass.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_class, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 2;
                final Intent intent=new Intent(mContext, VideoActivity.class);
                intent.putExtra("id",data.get(position).getId()+"");
                intent.putExtra("title",data.get(position).getTypename());
                intent.putExtra("price",data.get(position).getPrice());
                intent.putExtra("content",data.get(position).getContent());
                mCache = ACache.get(mContext);
                //从缓存读取用户信息
                userInfo = (UserInfo) mCache.getAsObject("userInfo");
                listener = new ObserverOnNextListener<No>() {
                    @Override
                    public void onNext(No data) {
                        mContext.startActivity(intent);
                    }
                };

                if (userInfo!=null){
                    String mid=data.get(position).getId()+"";
                    ApiMethods.getVideoClassNum(new MyObserver<No>(listener),  mid, userInfo.getId()+"",(RxAppCompatActivity) mContext);
                }else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();

                }


            }
                //notifyDataSetChanged();//刷新

        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = data.get(position).getTypename();
        holder.title.setText(title);
        String imageUrl="http://btsc.botian120.com"+data.get(position).getLitpic();
        //int width = (int) (ScreenSizeUtils.getInstance(mContext).getScreenWidth() * 0.47f);
        if (position==0){
            holder.topLine.setVisibility(View.VISIBLE);
        }
        Glide.with(mContext)
                .load(imageUrl)
                .centerCrop()
                .into(holder.imageView);

        String price="¥"+data.get(position).getPrice();
        holder.price.setText(price);
        String views="浏览量:"+data.get(position).getClick();
        holder.views.setText(views);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

