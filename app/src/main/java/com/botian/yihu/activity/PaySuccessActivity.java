package com.botian.yihu.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
