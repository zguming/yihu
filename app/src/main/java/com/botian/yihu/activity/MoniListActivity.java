package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.MoniListAdapter;
import com.botian.yihu.adapter.NewsCommentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.GetNewsComment;
import com.botian.yihu.beans.MoniTest;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoniListActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    List<MoniTest.DataBean> listData = new ArrayList<>();
    ObserverOnNextListener<MoniTest> listener;
    @BindView(R.id.back)
    ImageView back;
    private ACache mCache;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moni_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MoniListActivity.this);
        commentRecyclerView.setLayoutManager(layoutManager);
        listener = new ObserverOnNextListener<MoniTest>() {
            @Override
            public void onNext(MoniTest data) {
                listData.addAll(data.getData());
                MoniListAdapter adapter = new MoniListAdapter(MoniListActivity.this, listData);
                commentRecyclerView.setAdapter(adapter);
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        ApiMethods.getMoniTestPlace(new ProgressObserver<MoniTest>(MoniListActivity.this, listener), this);
    }



    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
