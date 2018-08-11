package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MyMoney;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MymoneyActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.my_money)
    TextView myMoney;
    @BindView(R.id.pay_money)
    Button payMoney;
    private ACache mCache;
    private UserInfo userInfo;
    String id;
    ObserverOnNextListener<MyMoney> listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymoney);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        listener = new ObserverOnNextListener<MyMoney>() {
            @Override
            public void onNext(MyMoney data) {
                myMoney.setText(data.getData().getMoney());
            }
        };
        id=userInfo.getId()+"";
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiMethods.getMyMoney(new ProgressObserver<MyMoney>(this, listener), id, this);

    }

    @OnClick({R.id.back, R.id.pay_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay_money:
                Intent intent=new Intent(this,PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
