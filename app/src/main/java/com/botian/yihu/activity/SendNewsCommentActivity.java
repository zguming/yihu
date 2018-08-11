package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.SendNewsComment;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

public class SendNewsCommentActivity extends RxAppCompatActivity {
    @BindView(R.id.fjEdit)
    FJEditTextCount fjEdit;
    @BindView(R.id.submit)
    Button submit;
    String topic_id;
    String cl;
    @BindView(R.id.back)
    ImageView back;
    private ACache mCache;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        Intent intent = getIntent();
        topic_id = intent.getStringExtra("id");
    }

    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                String str = fjEdit.getText();
                if (!str.equals("")) {
                    ObserverOnNextListener<SendNewsComment> listener = new ObserverOnNextListener<SendNewsComment>() {
                        @Override
                        public void onNext(SendNewsComment data) {
                            Toast.makeText(SendNewsCommentActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
                            if (data.getCode() == 200) {
                                finish();
                            }
                        }
                    };
                    ApiMethods.sendNewComment(new ProgressObserver<SendNewsComment>(SendNewsCommentActivity.this, listener),topic_id, userInfo.getId() + "", str, this);
                    break;
                } else {
                    Toast.makeText(SendNewsCommentActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }

}
