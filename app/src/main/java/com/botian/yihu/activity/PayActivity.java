package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.PayBeans;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.mob.paysdk.AliPayAPI;
import com.mob.paysdk.MobPayAPI;
import com.mob.paysdk.OnPayListener;
import com.mob.paysdk.PayOrder;
import com.mob.paysdk.PayResult;
import com.mob.paysdk.PaySDK;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends RxAppCompatActivity {

    @BindView(R.id.btn_topay)
    Button btnTopay;
    @BindView(R.id.rb_money_1)
    CheckBox rbMoney1;
    @BindView(R.id.rb_money_2)
    CheckBox rbMoney2;
    @BindView(R.id.rb_money_3)
    CheckBox rbMoney3;
    @BindView(R.id.rb_money_4)
    CheckBox rbMoney4;
    @BindView(R.id.rb_money_5)
    CheckBox rbMoney5;
    @BindView(R.id.rb_money_6)
    CheckBox rbMoney6;
    @BindView(R.id.ed_money)
    EditText edMoney;
    @BindView(R.id.tv_pay_tota2)
    TextView tvPayTota2;
    @BindView(R.id.tv_order_list)
    TextView tvOrderList;
    @BindView(R.id.back)
    ImageView back;
    private CheckBox checkBox;
    private String money = "0";
    private ACache mCache;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        edMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvPayTota2.setText(edMoney.getText().toString());
            }
        });

    }

    @OnClick(R.id.btn_topay)
    public void onViewClicked() {
        String num = tvPayTota2.getText().toString();
        int money = Integer.parseInt(num);
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = userInfo.getId() + String.format("%010d", timeStampSec) + "";
        PayOrder order = new PayOrder();
        order.setOrderNo(timestamp);
        order.setAmount(money * 100);
        order.setSubject("充值");
        order.setBody(userInfo.getUsername());
        // 如果想获取支付宝的支付api，可以这样子做：
        AliPayAPI alipay = PaySDK.createMobPayAPI(AliPayAPI.class);
        // 如果想获取微信的支付api，可以这样子做：
        //WXPayAPI wxpay = PaySDK.createMobPayAPI(WXPayAPI.class);
        alipay.pay(order, new OnPayListener<PayOrder>() {
            @Override
            public boolean onWillPay(String ticketId, PayOrder order, MobPayAPI api) {
                // TODO 保存本次支付操作的 ticketId
                // 返回false表示不阻止本次支付
                return false;
            }

            @Override
            public void onPayEnd(PayResult payResult, PayOrder order, MobPayAPI api) {
                // TODO 处理支付的结果，成功或失败可以在payResult中获取
                ObserverOnNextListener<PayBeans> listener = new ObserverOnNextListener<PayBeans>() {
                    @Override
                    public void onNext(PayBeans data) {
                        if (data.getCode() == 200) {
                            Intent intent=new Intent(PayActivity.this,PaySuccessActivity.class);
                            startActivity(intent);
                            //Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PayActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                };
                if (payResult.toString().equals("PAYRESULT_OK")) {
                    ApiMethods.payMoney(new ProgressObserver<PayBeans>(PayActivity.this, listener), userInfo.getId() + "", order.getOrderNo(), userInfo.getMoblie() + "", order.getAmount() * 100 + "", "1", PayActivity.this);
                } else {
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.rb_money_1, R.id.rb_money_2, R.id.rb_money_3, R.id.rb_money_4, R.id.rb_money_5, R.id.rb_money_6, R.id.ed_money, R.id.tv_order_list,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_money_1:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney1.isChecked()) {
                        money = "0";
                    } else {
                        money = "30";
                        if (!checkBox.equals(rbMoney1)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "30";
                }
                checkBox = rbMoney1;
                tvPayTota2.setText(money);
                break;
            case R.id.rb_money_2:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney2.isChecked()) {
                        money = "0";
                    } else {
                        money = "50";
                        if (!checkBox.equals(rbMoney2)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "50";
                }
                checkBox = rbMoney2;
                tvPayTota2.setText(money);
                break;
            case R.id.rb_money_3:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney3.isChecked()) {
                        money = "0";
                    } else {
                        money = "100";
                        if (!checkBox.equals(rbMoney3)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "100";
                }
                checkBox = rbMoney3;
                tvPayTota2.setText(money);
                break;
            case R.id.rb_money_4:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney4.isChecked()) {
                        money = "0";
                    } else {
                        money = "200";
                        if (!checkBox.equals(rbMoney4)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "200";
                }
                checkBox = rbMoney4;
                tvPayTota2.setText(money);
                break;
            case R.id.rb_money_5:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney5.isChecked()) {
                        money = "0";
                    } else {
                        money = "300";
                        if (!checkBox.equals(rbMoney5)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "300";
                }
                checkBox = rbMoney5;
                tvPayTota2.setText(money);
                break;
            case R.id.rb_money_6:
                edMoney.getText().clear();
                if (checkBox != null) {
                    if (!rbMoney6.isChecked()) {
                        money = "0";
                    } else {
                        money = "500";
                        if (!checkBox.equals(rbMoney6)) {
                            checkBox.setChecked(false);
                        }
                    }
                } else {
                    money = "500";
                }
                checkBox = rbMoney6;
                tvPayTota2.setText(money);
                break;
            case R.id.ed_money:
                if (checkBox != null) {
                    checkBox.setChecked(false);
                    checkBox = null;
                    money = "0";
                    tvPayTota2.setText(money);
                }
                break;
            case R.id.tv_order_list:
                Intent intent = new Intent(this, PayorderActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}
