package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WrongActivity extends RxAppCompatActivity {
    @BindView(R.id.linear_chapter_practice)
    LinearLayout linearChapterPractice;
    @BindView(R.id.linear_simulation_test)
    LinearLayout linearSimulationTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.linear_chapter_practice, R.id.linear_simulation_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_chapter_practice:
                Intent intent=new Intent(this,WrongChapterPracticeActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_simulation_test:
                break;
        }
    }
}
