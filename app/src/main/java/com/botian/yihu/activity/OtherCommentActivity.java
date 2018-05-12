package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.OtherCommentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.CommentParcel;
import com.botian.yihu.data.OtherCommentBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherCommentActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    @BindView(R.id.rl_send)
    RelativeLayout rlSend;
    @BindView(R.id.rl_praise)
    RelativeLayout rlPraise;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    CommentParcel commentParcel;
    List<OtherCommentBean.DataBeanX.DataBean>listData=new ArrayList<>();
    TextView comment;
    View header;
    TextView title;
    int current_page;//当前分页
    int last_page;//总分页
    ObserverOnNextListener<OtherCommentBean> listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        commentParcel = intent.getParcelableExtra("commentParcel");
        LinearLayoutManager layoutManager = new LinearLayoutManager(OtherCommentActivity.this);
        commentRecyclerView.setLayoutManager(layoutManager);
        header = LayoutInflater.from(OtherCommentActivity.this).inflate(R.layout.header_other_comment, null);
        title = header.findViewById(R.id.title);
        comment = header.findViewById(R.id.comment);
        listener = new ObserverOnNextListener<OtherCommentBean>() {
            @Override
            public void onNext(OtherCommentBean data) {
                setAdapter(data);
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "","1","20",this);
    }
    public void setAdapter(OtherCommentBean data){
        current_page=data.getData().getCurrent_page();
        last_page=data.getData().getLast_page();
        listData.addAll(data.getData().getData());
        if(current_page==1){//判断是否初次加载
            String a=data.getData().getTotal()+"";
            comment.setText(a);
            title.setText(commentParcel.getTitle());
            commentRecyclerView.addHeaderView(header);
            OtherCommentAdapter adapter = new OtherCommentAdapter(OtherCommentActivity.this,this, listData);
            commentRecyclerView.setAdapter(adapter);
        }else {
            commentRecyclerView.loadMoreComplete();
        }
        if(current_page==last_page){
            commentRecyclerView.setLoadingMoreEnabled(false);
        }
        commentRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
            }

            @Override
            public void onLoadMore() {
                // load more data here
                if(current_page==last_page){
                }else {
                    ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20",OtherCommentActivity.this);
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
    }

    @OnClick({R.id.rl_send, R.id.rl_praise, R.id.rl_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_send:
                break;
            case R.id.rl_praise:
                break;
            case R.id.rl_comment:
                Intent intent=new Intent(this,SendCommentActivity.class);
                intent.putExtra("topic_id", commentParcel.getId());
                startActivity(intent);
                break;
        }
    }
}
