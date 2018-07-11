package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.NewsCommentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.GetNewsComment;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsCommentActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    List<GetNewsComment.DataBeanX.DataBean> listData = new ArrayList<>();
    int current_page;//当前分页
    int last_page;//总分页
    ObserverOnNextListener<GetNewsComment> listener;
    ObserverOnNextListener<GetNewsComment> listener1;
    String filter1;
    String id;
    @BindView(R.id.back)
    ImageView back;
    private ACache mCache;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        LinearLayoutManager layoutManager = new LinearLayoutManager(NewsCommentActivity.this);
        commentRecyclerView.setLayoutManager(layoutManager);
        listener = new ObserverOnNextListener<GetNewsComment>() {
            @Override
            public void onNext(GetNewsComment data) {
                //zanNum=data.getData().getTotal()+"";
                setAdapter(data.getData());
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        listener1 = new ObserverOnNextListener<GetNewsComment>() {
            @Override
            public void onNext(GetNewsComment data) {
                setAdapter(data.getData());
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        filter1 = "mid,eq," + id;
        ApiMethods.getNewComment(new ProgressObserver<GetNewsComment>(NewsCommentActivity.this, listener), filter1, "1", "30", this);
    }

    public void setAdapter(GetNewsComment.DataBeanX data) {
        current_page = data.getCurrent_page();
        last_page = data.getLast_page();
        listData.addAll(data.getData());
        if (current_page == 1) {//判断是否初次加载
            String a = data.getTotal() + "";
            //comment.setText(a);
            //title.setText(commentParcel.getTitle());
            //commentRecyclerView.addHeaderView(header);
            NewsCommentAdapter adapter = new NewsCommentAdapter(NewsCommentActivity.this, listData);
            commentRecyclerView.setAdapter(adapter);
        } else {
            commentRecyclerView.loadMoreComplete();
        }
        if (current_page == last_page) {
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
                if (current_page == last_page) {
                } else {
                    ApiMethods.getNewComment2(new MyObserver<GetNewsComment>(listener1), filter1, current_page + 1 + "", "30", NewsCommentActivity.this);
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
    }

    @OnClick({R.id.back,R.id.rl_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_comment:
                if (userInfo == null) {
                    Toast.makeText(NewsCommentActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, SendNewsCommentActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
