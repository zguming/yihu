package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsContentActivity extends RxAppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String titleStr = intent.getStringExtra("title");
        String contentStr = intent.getStringExtra("content");
        title.setText(titleStr);
        content.setText(contentStr);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
