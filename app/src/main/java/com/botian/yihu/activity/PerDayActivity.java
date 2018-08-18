package com.botian.yihu.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerDayActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_chapter)
    TextView tvChapter;
    @BindView(R.id.zhenti)
    TextView zhenti;
    private SharedPreferences pref;
    //每日做题情况统计
    private int perdayChCorrect;
    private int perdayChWrong;
    private int perdayZhCorrect;
    private int perdayZhWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_day);
        ButterKnife.bind(this);
        pref = getSharedPreferences("perday", MODE_PRIVATE);
        perdayZhCorrect = pref.getInt("perdayZhCorrect", 0);
        perdayZhWrong = pref.getInt("perdayZhWrong", 0);
        perdayChCorrect = pref.getInt("perdayChCorrect", 0);
        perdayChWrong = pref.getInt("perdayChWrong", 0);
        int a1 = perdayChCorrect * 100;
        int a2 = perdayChCorrect + perdayChWrong;
        int b1 = perdayZhCorrect * 100;
        int b2 = perdayZhCorrect + perdayZhWrong;
        String aa;
        String bb;
        if (a2 != 0) {
            aa = a1 / a2 + "";
        }else {
            aa = 0 + "";
        }
        if (b2 != 0) {
            bb = b1 / b2 + "";
        } else {
            bb = 0 + "";
        }
        //String bb=(perdayZhCorrect*100)/(perdayZhCorrect+perdayZhWrong)+"";
        String str1 = "共做" + a2 + "道题，" + "做对" + perdayChCorrect + "道，"
                + "做错" + perdayChWrong + "道，" + "正确率" + aa + "%";
        String str2 = "共做" + b2 + "道题，" + "做对" + perdayZhCorrect + "道，"
                + "做错" + perdayZhWrong + "道，" + "正确率" + bb + "%";
        tvChapter.setText(str1);
        zhenti.setText(str2);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
