package com.botian.yihu.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.MoniBuyActivity;
import com.botian.yihu.activity.SimulationTestActivity2;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.SearchMoniBuy;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class MoniListAdapter extends RecyclerView.Adapter<MoniListAdapter.MyViewHolder> {



    private List<MoniTest.DataBean> data;
    private Context mContext;
    private String userid;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.core)
        TextView core;
        @BindView(R.id.buy22)
        TextView buy22;
        @BindView(R.id.buy33)
        TextView buy33;
        @BindView(R.id.tb_right)
        ImageView tbRight;
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public MoniListAdapter(Context mContext, List<MoniTest.DataBean> data, String userid) {
        this.mContext = mContext;
        this.data = data;
        this.userid = userid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moni_list, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                final int position = myViewHolder.getAdapterPosition() - 1;
                ObserverOnNextListener<SearchMoniBuy> listener21 = new ObserverOnNextListener<SearchMoniBuy>() {
                    @Override
                    public void onNext(final SearchMoniBuy data1) {

                        if (data1.getCode() != 200) {
                            // 创建构建器
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            // 设置参数
                            builder.setTitle("购买")
                                    .setMessage("是否确定购买")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            Intent intent = new Intent(mContext, MoniBuyActivity.class);
                                            intent.putExtra("money", data.get(position).getMoney());
                                            intent.putExtra("id", data.get(position).getId() + "");
                                            intent.putExtra("place", data.get(position).getTypename() + "");
                                            mContext.startActivity(intent);
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    //Toast.makeText(MainActivity.this, "一点也不老实", 0)
                                    // .show();
                                }
                            });
                            builder.create().show();
                        } else {
                            Intent intentr = new Intent(mContext, SimulationTestActivity2.class);
                            intentr.putExtra("mid", SubjectUtil.getSubjectNo2()+"");
                            intentr.putExtra("typeid", data.get(position).getId()+"");
                            intentr.putExtra("time", data.get(position).getExamination());
                            intentr.putExtra("score", data.get(position).getFartion());
                            mContext.startActivity(intentr);
                        }

                    }
                };
                ApiMethods.searchMoniBuy(new MyObserver<SearchMoniBuy>(listener21), userid + "", SubjectUtil.getSubjectNo2() + "", data.get(position).getId() + "", (RxAppCompatActivity) mContext);

                //notifyDataSetChanged();//刷新
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getTypename());
        String time1 = "时间：" + data.get(position).getExamination() + "分钟";
        String score = "分数：" + data.get(position).getFartion();
        holder.time.setText(time1);
        holder.core.setText(score);
        ObserverOnNextListener<SearchMoniBuy> listener21 = new ObserverOnNextListener<SearchMoniBuy>() {
            @Override
            public void onNext(SearchMoniBuy data1) {

                if (data1.getCode() != 200) {
                    holder.buy22.setVisibility(View.VISIBLE);
                    String money=data.get(position).getMoney()+"金币";
                    holder.buy22.setText(money);
                    holder.buy33.setVisibility(View.VISIBLE);
                    holder.tbRight.setVisibility(View.GONE);
                } else {
                    holder.buy22.setVisibility(View.GONE);
                    holder.buy33.setVisibility(View.GONE);
                    holder.tbRight.setVisibility(View.VISIBLE);
                }

            }
        };
        ApiMethods.searchMoniBuy(new MyObserver<SearchMoniBuy>(listener21), userid + "", SubjectUtil.getSubjectNo2() + "", data.get(position).getId() + "", (RxAppCompatActivity) mContext);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
