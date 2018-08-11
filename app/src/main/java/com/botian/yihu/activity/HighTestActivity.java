package com.botian.yihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.HighTestAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.HighTest;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.SubjectUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HighTestActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    @BindView(R.id.back)
    ImageView back;
    //ObserverOnNextListener<MyCollection> listener;
    //private ACache mCache;
    //private UserInfo userInfo;
    HighTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heigh_test);
        ButterKnife.bind(this);
        ObserverOnNextListener<HighTest> listener = new ObserverOnNextListener<HighTest>() {
            @Override
            public void onNext(HighTest data) {
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                commentRecyclerView.setLoadingMoreEnabled(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(HighTestActivity.this);
                commentRecyclerView.setLayoutManager(layoutManager);
                adapter = new HighTestAdapter(HighTestActivity.this, data.getData());
                commentRecyclerView.setAdapter(adapter);
            }
        };
        ApiMethods.getHighTest(new ProgressObserver<HighTest>(HighTestActivity.this, listener), "mid,eq,"+SubjectUtil.getSubjectNo(),"mids,eq,"+SubjectUtil.getSubjectNo2(), this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
