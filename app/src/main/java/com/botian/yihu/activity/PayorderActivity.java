package com.botian.yihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.OrderListAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.PayOrder;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayorderActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private ACache mCache;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payorder);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<PayOrder> listener = new ObserverOnNextListener<PayOrder>() {
            @Override
            public void onNext(PayOrder data) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(PayorderActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                OrderListAdapter adapter = new OrderListAdapter(PayorderActivity.this, data.getData());
                recyclerView.setAdapter(adapter);
                //禁用下拉刷新和加载更多功能
                recyclerView.setPullRefreshEnabled(false);
                recyclerView.setLoadingMoreEnabled(false);
            }
        };
        String filter = "userid,eq," + userInfo.getId();
        ApiMethods.getPayOrder(new ProgressObserver<PayOrder>(this, listener), filter,  this);

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
