package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MistakeBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

public class FeedbackErrorActivity extends RxAppCompatActivity {
    @BindView(R.id.fjEdit)
    FJEditTextCount fjEdit;
    @BindView(R.id.submit)
    TextView submit;
    String userId;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_error);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
    }

    @OnClick({ R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                String str = fjEdit.getText();
                if (!str.equals("")) {
                    ObserverOnNextListener<MistakeBean> listener = new ObserverOnNextListener<MistakeBean>() {
                        @Override
                        public void onNext(MistakeBean data) {
                            Toast.makeText(FeedbackErrorActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                            if (data.getCode() == 200) {
                                finish();
                            }
                        }
                    };
                    ApiMethods.getMistake(new ProgressObserver<MistakeBean>(FeedbackErrorActivity.this, listener), userId, str, this);
                    break;
                } else {
                    Toast.makeText(FeedbackErrorActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
