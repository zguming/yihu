package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.MyCollectionPracticeActivity;
import com.botian.yihu.data.CollectionRecordsBean;
import com.botian.yihu.data.CollectionRecordsParcel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyViewHolder> {
    private List<CollectionRecordsBean.DataBean> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.num)
        TextView num;
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

    public MyCollectAdapter(Context mContext, List<CollectionRecordsBean.DataBean> data) {
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
                CollectionRecordsParcel collectionRecordsParcel = new CollectionRecordsParcel();
                List<CollectionRecordsParcel.DataBean> dataBeanList=new ArrayList<>();
                for (int i=0;i<data.size();i++){
                    CollectionRecordsParcel.DataBean dataBean1 = new CollectionRecordsParcel.DataBean();
                    dataBean1.setTopic_id(data.get(i).getTopic_id());
                    dataBean1.setUser_id(data.get(i).getUser_id());
                    dataBean1.setName(data.get(i).getName());
                    dataBean1.setA(data.get(i).getA());
                    dataBean1.setB(data.get(i).getB());
                    dataBean1.setC(data.get(i).getC());
                    dataBean1.setD(data.get(i).getD());
                    dataBean1.setE(data.get(i).getE());
                    dataBean1.setCorrect(data.get(i).getCorrect());
                    dataBean1.setImage(data.get(i).getImage());
                    dataBean1.setAnalysis(data.get(i).getAnalysis());
                    dataBeanList.add(dataBean1);
                }
                collectionRecordsParcel.setData(dataBeanList);
                collectionRecordsParcel.setPosition(position);
                Intent intent=new Intent(mContext, MyCollectionPracticeActivity.class);
                intent.putExtra("collectionRecordsParcel", collectionRecordsParcel);
                mContext.startActivity(intent);
                //notifyDataSetChanged();//刷新
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String num=position+1+".";
        holder.num.setText(num);
        String name = data.get(position).getName();
        holder.title.setText(name);
        if (position==0){
            holder.topLine.setVisibility(View.VISIBLE);
        }
        String answer;
        switch (data.get(position).getCorrect()) {
            case "A":
                answer = data.get(position).getA();
                break;
            case "B":
                answer = data.get(position).getB();
                break;
            case "C":
                answer = data.get(position).getC();
                break;
            case "D":
                answer = data.get(position).getD();
                break;
            default:
                answer = data.get(position).getE();
                break;
        }
        String content = "答案: "+data.get(position).getCorrect()+"."+answer;
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

