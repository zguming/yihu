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
import com.botian.yihu.activity.ChapterPracticeSpecialActivity;
import com.botian.yihu.activity.ChapterPracticeTwoActivity;
import com.botian.yihu.data.ChapterPracticeIdParcel;
import com.botian.yihu.data.ChapterPracticeOneBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class ChapterPracticeOneAdapter extends RecyclerView.Adapter<ChapterPracticeOneAdapter.MyViewHolder> {
    private List<ChapterPracticeOneBean.DataBean> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_textView)
        TextView myTextView;
        @BindView(R.id.top_line)
        View topLine;
        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public ChapterPracticeOneAdapter(Context mContext, List<ChapterPracticeOneBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_practice_one, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 1;
                //String category = data.get(position);
                ChapterPracticeIdParcel practiceIDData = new ChapterPracticeIdParcel();
                practiceIDData.setCo_id(data.get(position).getCo_id() + "");
                practiceIDData.setId(data.get(position).getId() + "");
                if (data.get(position).getCo_id() == 1) {
                    Intent intent = new Intent(mContext, ChapterPracticeTwoActivity.class);
                    intent.putExtra("practiceIdData", practiceIDData);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, ChapterPracticeSpecialActivity.class);
                    intent.putExtra("practiceIdData", practiceIDData);
                    mContext.startActivity(intent);
                }
                //notifyDataSetChanged();//刷新
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = data.get(position).getTitle();
        holder.myTextView.setText(title);
        if (position==0){
            holder.topLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

