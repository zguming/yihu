package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.ChapterPracticeTwoAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.ChapterPracticeIdParcel;
import com.botian.yihu.data.ChapterPracticeTwoBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChapterPracticeTwoActivity extends RxAppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    private String co_id;
    private String id;
    @BindView(R.id.chapter_practice_two_recycler_view)
    XRecyclerView chapterPracticeTwoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_two);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ChapterPracticeIdParcel practiceIdData = intent.getParcelableExtra("practiceIdData");
        co_id = practiceIdData.getCo_id();
        id = practiceIdData.getId();
        ObserverOnNextListener<ChapterPracticeTwoBean> listener = new ObserverOnNextListener<ChapterPracticeTwoBean>() {
            @Override
            public void onNext(ChapterPracticeTwoBean data) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(ChapterPracticeTwoActivity.this);
                chapterPracticeTwoRecyclerView.setLayoutManager(layoutManager);
                ChapterPracticeTwoAdapter adapter = new ChapterPracticeTwoAdapter(ChapterPracticeTwoActivity.this, ChapterPracticeTwoActivity.this, data.getData(), co_id, id);
                chapterPracticeTwoRecyclerView.setAdapter(adapter);
                //禁用下拉刷新和加载更多功能
                chapterPracticeTwoRecyclerView.setPullRefreshEnabled(false);
            }
        };
        ApiMethods.getChapterPracticeTwo(new ProgressObserver<ChapterPracticeTwoBean>(ChapterPracticeTwoActivity.this, listener), co_id, id, this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
