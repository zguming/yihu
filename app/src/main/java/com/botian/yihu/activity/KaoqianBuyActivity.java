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
import com.botian.yihu.beans.KaoQianBuy;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.KaoqianBuyEvent;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KaoqianBuyActivity extends RxAppCompatActivity {

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
    private String money;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay1);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        money = intent.getStringExtra("price");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        String price1 = "价格" + money + "金币";
        String price2 = money + "金币";
        tvUnitPrice.setText(price1);
        tvPayCount.setText(price2);
        tvClassname.setText(name);
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
                ObserverOnNextListener<KaoQianBuy> listener211 = new ObserverOnNextListener<KaoQianBuy>() {
                    @Override
                    public void onNext(KaoQianBuy data1) {

                        if (data1.getCode() == 200) {
                            EventBus.getDefault().post(new KaoqianBuyEvent());
                            Intent intent = new Intent(KaoqianBuyActivity.this, PaySuccessActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(KaoqianBuyActivity.this, data1.getMsg(), Toast.LENGTH_SHORT).show();

                        }

                    }
                };
                ApiMethods.KaoQianBuy(new ProgressObserver<KaoQianBuy>(KaoqianBuyActivity.this, listener211), userInfo.getId() + "", id + "", KaoqianBuyActivity.this);

                break;
        }
    }
}
