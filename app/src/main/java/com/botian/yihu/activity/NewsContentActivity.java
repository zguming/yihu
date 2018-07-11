package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsContentActivity extends RxAppCompatActivity {
    @BindView(R.id.show)
    WebView show;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    String id;
    @BindView(R.id.back)
    ImageView back;
    private ACache mCache;
    private UserInfo userInfo;
    private ObserverOnNextListener<No> listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String contentStr = intent.getStringExtra("content");
        id = intent.getStringExtra("id");
        String text = Html.fromHtml(contentStr).toString();
        //http://btsc.botian120.com
        String cc = text.replaceAll("<img src=\"", "<img src=\"http://btsc.botian120.com");
        show.loadDataWithBaseURL(null, getNewContent(cc), "text/html", "utf-8", null);
        listener = new ObserverOnNextListener<No>() {
            @Override
            public void onNext(No data) {
            }
        };
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        if (userInfo!=null){
            ApiMethods.sendNewNum(new MyObserver<No>(listener),  id, userInfo.getId()+"", this);
        }
    }


    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            Document doc = Jsoup.parse(htmltext);
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }


    @OnClick({R.id.back, R.id.rl_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_comment:
                Intent intent = new Intent(this, NewsCommentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }
}
