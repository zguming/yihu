package com.botian.yihu.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.ChapterPracticeOneAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ChapterPracticeOneBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChapterPracticeOneActivity extends RxAppCompatActivity {
    @BindView(R.id.chapter_practice_one_recycler_view)
    XRecyclerView chapterPracticeOneRecyclerView;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_one);
        ButterKnife.bind(this);
        ObserverOnNextListener<ChapterPracticeOneBean> listener = new ObserverOnNextListener<ChapterPracticeOneBean>() {
            @Override
            public void onNext(ChapterPracticeOneBean data) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(ChapterPracticeOneActivity.this);
                chapterPracticeOneRecyclerView.setLayoutManager(layoutManager);
                ChapterPracticeOneAdapter adapter = new ChapterPracticeOneAdapter(ChapterPracticeOneActivity.this, data.getData());
                chapterPracticeOneRecyclerView.setAdapter(adapter);
                //禁用下拉刷新和加载更多功能
                chapterPracticeOneRecyclerView.setPullRefreshEnabled(false);
            }
        };
        SharedPreferences pref = getSharedPreferences("subjectSelectData", MODE_PRIVATE);
        String subjectNo = pref.getInt("subjectNo", 1) + "";
        ApiMethods.getChapterPracticeOne(new ProgressObserver<ChapterPracticeOneBean>(ChapterPracticeOneActivity.this, listener), subjectNo, this);
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
