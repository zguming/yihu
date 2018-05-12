package com.botian.yihu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.MyCollectAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.CollectionRecordsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    ObserverOnNextListener<CollectionRecordsBean> listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        listener = new ObserverOnNextListener<CollectionRecordsBean>() {
            @Override
            public void onNext(CollectionRecordsBean data) {
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                commentRecyclerView.setLoadingMoreEnabled(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyCollectActivity.this);
                commentRecyclerView.setLayoutManager(layoutManager);
                MyCollectAdapter adapter = new MyCollectAdapter(MyCollectActivity.this, data.getData());
                commentRecyclerView.setAdapter(adapter);
            }
        };
        ApiMethods.getCollectionRecords(new ProgressObserver<CollectionRecordsBean>(MyCollectActivity.this, listener),  "2646",this);

    }
}
