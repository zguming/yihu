package com.botian.yihu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.OtherCommentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.CommentParcel;
import com.botian.yihu.beans.OtherCommentBean;
import com.botian.yihu.beans.TopicCommentZip;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.ZanBean;
import com.botian.yihu.util.ACache;
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
    @BindView(R.id.rl_praise)
    RelativeLayout rlPraise;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    CommentParcel commentParcel;
    List<OtherCommentBean.DataBeanX.DataBean> listData = new ArrayList<>();
    TextView comment;
    TextView zan;
    View header;
    TextView title;
    int current_page;//当前分页
    int last_page;//总分页
    ObserverOnNextListener<TopicCommentZip> listener;
    ObserverOnNextListener<OtherCommentBean> listener1;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    private ACache mCache;
    private UserInfo userInfo;
    private int iscal = 1;//1点赞，2取消
    String zanNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_comment);
        ButterKnife.bind(this);
        initView();
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        Intent intent = getIntent();
        commentParcel = intent.getParcelableExtra("commentParcel");
        LinearLayoutManager layoutManager = new LinearLayoutManager(OtherCommentActivity.this);
        commentRecyclerView.setLayoutManager(layoutManager);
        header = LayoutInflater.from(OtherCommentActivity.this).inflate(R.layout.header_other_comment, null);
        title = header.findViewById(R.id.title);
        comment = header.findViewById(R.id.comment);
        zan = header.findViewById(R.id.zan);
        listener = new ObserverOnNextListener<TopicCommentZip>() {
            @Override
            public void onNext(TopicCommentZip data) {
                zanNum = data.getData().getTotal() + "";
                setAdapter(data.getData2());
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        listener1 = new ObserverOnNextListener<OtherCommentBean>() {
            @Override
            public void onNext(OtherCommentBean data) {
                setAdapter(data.getData());
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        String filter1 = "mid,eq," + commentParcel.getId();
        ApiMethods.getTopicCommentZip(new ProgressObserver<TopicCommentZip>(OtherCommentActivity.this, listener), filter1, this);
    }

    public void initView() {
        Drawable mPraise = getResources().getDrawable(R.drawable.detail_like);
        mPraise.setBounds(0, 0, 40, 40);
        tvPraise.setCompoundDrawables(mPraise, null, null, null);
    }

    public void setAdapter(OtherCommentBean.DataBeanX data) {
        current_page = data.getCurrent_page();
        last_page = data.getLast_page();
        listData.addAll(data.getData());
        if (current_page == 1) {//判断是否初次加载
            String a = data.getTotal() + "";
            comment.setText(a);
            zan.setText(zanNum);
            title.setText(commentParcel.getTitle());
            commentRecyclerView.addHeaderView(header);
            OtherCommentAdapter adapter = new OtherCommentAdapter(OtherCommentActivity.this, this, listData);
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
                    ApiMethods.getComment(new MyObserver<OtherCommentBean>(listener1), commentParcel.getId() + "", current_page + 1 + "", "30", OtherCommentActivity.this);
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
    }

    @OnClick({R.id.rl_praise, R.id.rl_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_praise:
                ObserverOnNextListener<ZanBean> listener = new ObserverOnNextListener<ZanBean>() {
                    @Override
                    public void onNext(ZanBean data) {
                        String a = data.getMsg();
                        if (data.getCode() == 400) {
                            Toast.makeText(OtherCommentActivity.this, a, Toast.LENGTH_SHORT).show();
                        } else {
                            if (iscal == 1) {
                                Drawable mPraise = getResources().getDrawable(R.drawable.detail_like_p);
                                mPraise.setBounds(0, 0, 40, 40);
                                tvPraise.setCompoundDrawables(mPraise, null, null, null);
                                tvPraise.setTextColor(getResources().getColor(R.color.blue));
                                iscal = 2;
                            } else {
                                Drawable mPraise = getResources().getDrawable(R.drawable.detail_like);
                                mPraise.setBounds(0, 0, 40, 40);
                                tvPraise.setCompoundDrawables(mPraise, null, null, null);
                                tvPraise.setTextColor(getResources().getColor(R.color.black));
                                iscal = 1;
                            }
                        }


                    }
                };
                if (userInfo == null) {
                    Toast.makeText(OtherCommentActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    ApiMethods.getTiZan(new MyObserver<ZanBean>(listener), commentParcel.getId() + "", userInfo.getId() + "", commentParcel.getCl(), iscal + "", this);
                }
                break;
            case R.id.rl_comment:
                if (userInfo == null) {
                    Toast.makeText(OtherCommentActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, SendCommentActivity.class);
                    intent.putExtra("topic_id", commentParcel.getId());
                    intent.putExtra("cl", commentParcel.getCl());
                    startActivity(intent);
                }
                break;
        }
    }
}
