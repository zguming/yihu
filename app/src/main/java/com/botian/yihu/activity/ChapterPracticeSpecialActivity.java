package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.ChapterPracticeSpecialAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ChapterPracticeIdParcel;
import com.botian.yihu.beans.ChapterPracticeOneBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//此类适用于护师执业及主管护师执业，不适用与护士执业
public class ChapterPracticeSpecialActivity extends RxAppCompatActivity {

    @BindView(R.id.chapter_practice_special_recycler_view)
    XRecyclerView chapterPracticeSpecialRecyclerView;
    @BindView(R.id.back)
    ImageView back;
    private String co_id;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_special);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ChapterPracticeIdParcel practiceIdData = intent.getParcelableExtra("practiceIdData");
        co_id = practiceIdData.getCo_id();
        id = practiceIdData.getId();
        ObserverOnNextListener<ChapterPracticeOneBean> listener = new ObserverOnNextListener<ChapterPracticeOneBean>() {
            @Override
            public void onNext(ChapterPracticeOneBean data) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(ChapterPracticeSpecialActivity.this);
                chapterPracticeSpecialRecyclerView.setLayoutManager(layoutManager);
                ChapterPracticeSpecialAdapter adapter = new ChapterPracticeSpecialAdapter(ChapterPracticeSpecialActivity.this, data.getData());
                chapterPracticeSpecialRecyclerView.setAdapter(adapter);
                //禁用下拉刷新和加载更多功能
                chapterPracticeSpecialRecyclerView.setPullRefreshEnabled(false);
            }
        };
        ApiMethods.getChapterPracticeSpecial(new ProgressObserver<ChapterPracticeOneBean>(ChapterPracticeSpecialActivity.this, listener), co_id, id, this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
