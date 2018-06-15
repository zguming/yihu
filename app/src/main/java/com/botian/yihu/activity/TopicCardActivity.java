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
    @BindView(R.id.already)
    TextView already;
    @BindView(R.id.yet)
    TextView yet;
    @BindView(R.id.correct)
    TextView correct;
    @BindView(R.id.false1)
    TextView false3;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    private ArrayList<Integer> topicCard = new ArrayList<>();
    private int already1;//已答总数
    private int correct1;//答对总数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_card);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        topicCard = intent.getIntegerArrayListExtra("topicCard");
        already1 = intent.getIntExtra("already", 9);
        correct1 = intent.getIntExtra("correct", 9);
        initView();
        //禁用下拉刷新和加载更多功能
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TopicCardAdapter adapter = new TopicCardAdapter(this, topicCard);
        recyclerView.setAdapter(adapter);

    }

    public void initView() {
        String a = topicCard.size() + "";
        all.setText(a);
        String b = already1 + "";
        already.setText(b);
        String c = correct1 + "";
        correct.setText(c);
        String d = topicCard.size() - already1 + "";
        yet.setText(d);
        String e = already1 - correct1 + "";
        false3.setText(e);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
