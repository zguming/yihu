package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.PlayLiveActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.KaoQianYaTiList;
import com.botian.yihu.beans.Live;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.ZhiBo;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
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
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.MyViewHolder> {


    private List<Live.DataBean> data;
    private Context mContext;
    private ObserverOnNextListener<No> listener;
    private ACache mCache;
    private UserInfo userInfo;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.data)
        TextView data;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.user_icon)
        CircleImageView userIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public LiveAdapter(Context mContext, List<Live.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 1;
                ObserverOnNextListener<ZhiBo> listener = new ObserverOnNextListener<ZhiBo>() {
                    @Override
                    public void onNext(ZhiBo data) {
                        Intent intent = new Intent(mContext, PlayLiveActivity.class);
                        intent.putExtra("url", data.getData().getData().get(0).getCode());
                        intent.putExtra("title", data.getData().getData().get(0).getTitle());
                        mContext.startActivity(intent);
                    }
                };


                ApiMethods.getZhibo(new MyObserver<ZhiBo>( listener),data.get(position).getId()+"", (RxAppCompatActivity)mContext);



            }


        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String data1=data.get(position).getDate().substring(5);
        holder.data.setText(data1);
        holder.title.setText(data.get(position).getTitle());
        String time = "时间 " + data.get(position).getTime_start() + "-" + data.get(position).getTime_end() + "    " + data.get(position).getZhteacher().getName();
        holder.time.setText(time);
        String picurl="http://btsc.botian120.com"+data.get(position).getZhteacher().getImg();
        Glide.with(mContext)
                .load(picurl)
                .centerCrop()
                .into(holder.userIcon);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

