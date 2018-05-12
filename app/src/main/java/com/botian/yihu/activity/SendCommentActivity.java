package com.botian.yihu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.SendCommentBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

public class SendCommentActivity extends RxAppCompatActivity {
    @BindView(R.id.fjEdit)
    FJEditTextCount fjEdit;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.submit)
    TextView submit;
    String topic_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        topic_id = intent.getIntExtra("topic_id", 0) + "";
    }

    @OnClick({R.id.cancel, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.submit:
                String str=fjEdit.getText();
                if(!str.equals("")){
                    ObserverOnNextListener<SendCommentBean> listener = new ObserverOnNextListener<SendCommentBean>() {
                        @Override
                        public void onNext(SendCommentBean data) {
                            Toast.makeText(SendCommentActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                            if (data.getCode()==1) {
                                finish();
                            }
                        }
                    };
                    ApiMethods.getSendComment(new ProgressObserver<SendCommentBean>(SendCommentActivity.this, listener), "2656", topic_id, str,this);
                    break;
                }else{
                    Toast.makeText(SendCommentActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
