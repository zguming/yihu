package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniBuy;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.MoniBuyEvent;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoniBuyActivity extends RxAppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_unit_price)
    TextView tvUnitPrice;
    @BindView(R.id.tv_pay_count)
    TextView tvPayCount;
    @BindView(R.id.tv_classname)
    TextView tvClassname;
    private ACache mCache;
    private UserInfo userInfo;
    private String place;
    private String typeid;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay1);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        place = intent.getStringExtra("place");
        typeid = intent.getStringExtra("id");
        money = intent.getStringExtra("money");
        String price="价格:"+money+"金币";
        tvUnitPrice.setText(price);
        String pay=money+"金币";
        tvPayCount.setText(pay);
        tvClassname.setText(place);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");

    }


    @OnClick({R.id.back, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_pay:
                ObserverOnNextListener<MoniBuy> listener = new ObserverOnNextListener<MoniBuy>() {
                    @Override
                    public void onNext(MoniBuy data) {
                        if (data.getCode() == 200) {
                            EventBus.getDefault().post(new MoniBuyEvent());
                            Intent intent = new Intent(MoniBuyActivity.this, PaySuccessActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MoniBuyActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                };
                ApiMethods.moniBuy(new ProgressObserver<MoniBuy>(MoniBuyActivity.this, listener), userInfo.getId() + "",  typeid, this);

                break;
        }
    }
}
