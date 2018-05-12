package com.botian.yihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.activity.PracticeAnswerActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.ChapterPracticeTwoBean;
import com.botian.yihu.data.PracticeBean;
import com.botian.yihu.data.PracticeParcel;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class ChapterPracticeTwoAdapter extends RecyclerView.Adapter<ChapterPracticeTwoAdapter.MyViewHolder> {
    private List<ChapterPracticeTwoBean.DataBean> data;
    private Context mContext;
    private RxAppCompatActivity yy;
    private String co_id;
    private String ch_id;
    private String zj_id;
    private String bar_id;

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

    public ChapterPracticeTwoAdapter(Context mContext,RxAppCompatActivity yy ,List<ChapterPracticeTwoBean.DataBean> data, String co_id, String ch_id) {
        this.mContext = mContext;
        this.yy = yy;
        this.data = data;
        this.co_id = co_id;
        this.ch_id = ch_id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_practice_two, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //RecyclerView的item点击事件 点击切换fragment
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                int position = myViewHolder.getAdapterPosition() - 1;
                zj_id = data.get(position).getId() + "";
                ObserverOnNextListener<PracticeBean> listener = new ObserverOnNextListener<PracticeBean>() {
                    @Override
                    public void onNext(PracticeBean data) {
                        //PracticeParcel.DataBean dataBean=new PracticeParcel.DataBean();
                        PracticeParcel practiceParcel = new PracticeParcel();
                        List<PracticeParcel.DataBean> dataBeanList = new ArrayList<>();
                        for (int i = 0; i < data.getData().size(); i++) {
                            PracticeParcel.DataBean dataBean1 = new PracticeParcel.DataBean();
                            dataBean1.setId(data.getData().get(i).getId());
                            dataBean1.setName(data.getData().get(i).getName());
                            dataBean1.setA(data.getData().get(i).getA());
                            dataBean1.setB(data.getData().get(i).getB());
                            dataBean1.setC(data.getData().get(i).getC());
                            dataBean1.setD(data.getData().get(i).getD());
                            dataBean1.setE(data.getData().get(i).getE());
                            dataBean1.setImage(data.getData().get(i).getImage());
                            dataBean1.setCorrect(data.getData().get(i).getCorrect());
                            dataBean1.setAnalysis(data.getData().get(i).getAnalysis());
                            dataBeanList.add(dataBean1);
                        }
                        practiceParcel.setData(dataBeanList);
                        Intent intent = new Intent(mContext, PracticeAnswerActivity.class);
                        intent.putExtra("practiceParcel", practiceParcel);
                        mContext.startActivity(intent);
                    }
                };
                if (co_id.equals("1")) {
                    bar_id = "0";
                } else {
                    bar_id = "54";
                }
                ApiMethods.getChapterPractice(new ProgressObserver<PracticeBean>(mContext, listener), co_id, ch_id, zj_id, bar_id,yy);
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

