package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebBrowserActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webbrowser);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        webView.loadUrl(url);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
