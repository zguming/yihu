package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.TopicCardAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicCardActivity extends AppCompatActivity {

    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    private ArrayList<Integer> topicCard = new ArrayList<>();
    private int already1;//已答总数
    private int correct1;//答对总数
    private String judge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_card);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        topicCard = intent.getIntegerArrayListExtra("topicCard");
        already1 = intent.getIntExtra("already", 9);
        correct1 = intent.getIntExtra("correct", 9);
        judge = intent.getStringExtra("switch");
        initView();
        //禁用下拉刷新和加载更多功能
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TopicCardAdapter adapter = new TopicCardAdapter(this, topicCard, judge);
        recyclerView.setAdapter(adapter);

    }

    public void initView() {
        String e = already1 - correct1 + "";
        String y=topicCard.size()-already1+"";
        String a = "共" + topicCard.size() + "题，" + "已答" + already1 + "题，" + "未答"+y+"题，"+"答对" + correct1 + "题，" + "答错" + e + "题";
        all.setText(a);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
