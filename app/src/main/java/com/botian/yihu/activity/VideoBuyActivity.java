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
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.VideoBuy;
import com.botian.yihu.eventbus.VideoBuyEvent;
import com.botian.yihu.eventbus.VideoBuyEvent2;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoBuyActivity extends RxAppCompatActivity {

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
    private String subject;
    private String typeid;
    private String money;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay1);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        typeid = intent.getStringExtra("id");
        money = intent.getStringExtra("money");
        title = intent.getStringExtra("title");
        String money3 = "价格："+money + "金币";
        String money4 = money + "金币";
        tvUnitPrice.setText(money3);
        tvPayCount.setText(money4);
        tvClassname.setText(title);
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
                ObserverOnNextListener<VideoBuy> listener = new ObserverOnNextListener<VideoBuy>() {
                    @Override
                    public void onNext(VideoBuy data) {
                        Toast.makeText(VideoBuyActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                        if (data.getCode() == 200) {
                            EventBus.getDefault().post(new VideoBuyEvent());
                            EventBus.getDefault().post(new VideoBuyEvent2());
                            Intent intent = new Intent(VideoBuyActivity.this, PaySuccessActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(VideoBuyActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                };

                ApiMethods.videoBuy(new ProgressObserver<VideoBuy>(this, listener), userInfo.getId() + "", SubjectUtil.getSubjectNo2() + "", typeid, "1", "1", this);
                break;
        }
    }
}
