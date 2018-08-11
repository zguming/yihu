package com.botian.yihu.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.activity.MyNotePracticeActivity;
import com.botian.yihu.database.NoteData;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 目录列表适配器
 * Created by Administrator on 2018/2/23 0023.
 */
public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyViewHolder> {
    private List<NoteData> data;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {
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

    public MyNoteAdapter(Context mContext, List<NoteData> data) {
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
                Intent intent = new Intent(mContext, MyNotePracticeActivity.class);
                //intent.putExtra("collectionRecordsParcel", collectionRecordsParcel);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
                //notifyDataSetChanged();//刷新
            }
        });
        myViewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                //XRecyclerView默认添加了一个header，因此要得到正确的position,需减去1
                final int position = myViewHolder.getAdapterPosition() - 1;
                // 创建构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                // 设置参数
                builder.setTitle("删除收藏")
                        .setMessage("是否确定删除此笔记")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                //Toast.makeText(MainActivity.this, "恭喜你答对了", 0)
                                // .show();
                                int id=data.get(position).getTopicId();
                                int cl=data.get(position).getCl();
                                int judge=data.get(position).getJudge();
                                DataSupport.deleteAll(NoteData.class,"topicId="+id+";"+"cl="+cl+";"+"judge="+1);
                                data.remove(position);
                                notifyDataSetChanged();
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
                return true;
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String num = position + 1 + ".";
        //holder.num.setText(num);
        String name = num+data.get(position).getName();
        holder.title.setText(name);
        if (position == 0) {
            holder.topLine.setVisibility(View.VISIBLE);
        }
        String answer;
        switch (data.get(position).getCorrect()) {
            case "A":
                answer = data.get(position).getA();
                break;
            case "B":
                answer = data.get(position).getA();
                break;
            case "C":
                answer = data.get(position).getA();
                break;
            case "D":
                answer = data.get(position).getA();
                break;
            default:
                answer = data.get(position).getA();
                break;
        }
        String content = "答案: " + data.get(position).getCorrect() + "." + answer;
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}