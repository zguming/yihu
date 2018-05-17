package com.botian.yihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.MyCollectAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.CollectionRecordsBean;
import com.botian.yihu.beans.MyCollection;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectionActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    ObserverOnNextListener<MyCollection> listener;
    private ACache mCache;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        mCache= ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        listener = new ObserverOnNextListener<MyCollection>() {
            @Override
            public void onNext(MyCollection data) {
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                commentRecyclerView.setLoadingMoreEnabled(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyCollectionActivity.this);
                commentRecyclerView.setLayoutManager(layoutManager);
                MyCollectAdapter adapter = new MyCollectAdapter(MyCollectionActivity.this, data.getData());
                commentRecyclerView.setAdapter(adapter);
            }
        };
        int userId=userInfo.getId();
        String filter="userid,eq,"+userId;
        ApiMethods.getCollectionRecords(new ProgressObserver<MyCollection>(MyCollectionActivity.this, listener),  filter,this);

    }
}
