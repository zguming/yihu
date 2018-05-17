package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.MyPagerAdapter;
import com.botian.yihu.beans.CommentParcel;
import com.botian.yihu.beans.PracticeData;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WrongChapterPracticeActivity extends RxAppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<View> viewsList;
    private MyPagerAdapter mAdapter;
    private List<PracticeData> practiceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_chapter_practice);
        ButterKnife.bind(this);
        practiceList = DataSupport.findAll(PracticeData.class);
        viewsList = new ArrayList<View>();
        initView();
        mAdapter = new MyPagerAdapter(viewsList);
        viewPager.setAdapter(mAdapter);
    }
    public void initView() {
        for (int i = 0; i < practiceList.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_answer, null, false);
            TextView timu = view.findViewById(R.id.timu);
            TextView current = view.findViewById(R.id.current_num);
            String currentNum=i+1+"";//当前数目
            current.setText(currentNum);
            TextView total = view.findViewById(R.id.total_num);
            String totalNum="/"+practiceList.size();//总数目
            total.setText(totalNum);
            final ImageView answerA = view.findViewById(R.id.answer_a);
            final TextView answerTextA = view.findViewById(R.id.answer_text_a);
            final LinearLayout linearAnswerA = view.findViewById(R.id.linear_answer_a);
            final ImageView answerB = view.findViewById(R.id.answer_b);
            final TextView answerTextB = view.findViewById(R.id.answer_text_b);
            final LinearLayout linearAnswerB = view.findViewById(R.id.linear_answer_b);
            final ImageView answerC = view.findViewById(R.id.answer_c);
            final TextView answerTextC = view.findViewById(R.id.answer_text_c);
            final LinearLayout linearAnswerC = view.findViewById(R.id.linear_answer_c);
            final ImageView answerD = view.findViewById(R.id.answer_d);
            final TextView answerTextD = view.findViewById(R.id.answer_text_d);
            final LinearLayout linearAnswerD = view.findViewById(R.id.linear_answer_d);
            final ImageView answerE = view.findViewById(R.id.answer_e);
            final TextView answerTextE = view.findViewById(R.id.answer_text_e);
            final LinearLayout linearAnswerE = view.findViewById(R.id.linear_answer_e);
            final LinearLayout bottomHide = view.findViewById(R.id.bottom_hide);
            final TextView tvTipsYourChoose = view.findViewById(R.id.tv_tips_yourchoose);
            final TextView btnLookOtherNote = view.findViewById(R.id.btnLookOtherNote);
            TextView check = view.findViewById(R.id.check);
            TextView analyse = view.findViewById(R.id.analyseinfo);
            timu.setText(practiceList.get(i).getName());
            answerTextA.setText(practiceList.get(i).getA());
            answerTextB.setText(practiceList.get(i).getB());
            answerTextC.setText(practiceList.get(i).getC());
            answerTextD.setText(practiceList.get(i).getD());
            answerTextE.setText(practiceList.get(i).getE());
            check.setText(practiceList.get(i).getCorrect());
            analyse.setText(practiceList.get(i).getAnalysis());
            final String correct = practiceList.get(i).getCorrect();
            final int finalI1 = i;
            linearAnswerA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("A".equals(correct)) {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.w_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        addDataBase(finalI1);
                        if (correct.equals("B")) {
                            answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                            answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("C")) {
                            answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                            answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("D")) {
                            answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                            answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("E")) {
                            answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                            answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        }
                    }
                    tvTipsYourChoose.setText("A");
                    bottomHide.setVisibility(View.VISIBLE);
                }
            });
            linearAnswerB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("B".equals(correct)) {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.w_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        addDataBase(finalI1);
                        if (correct.equals("A")) {
                            answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                            answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("C")) {
                            answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                            answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("D")) {
                            answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                            answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("E")) {
                            answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                            answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        }
                    }
                    tvTipsYourChoose.setText("B");
                    bottomHide.setVisibility(View.VISIBLE);
                }
            });
            linearAnswerC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("C".equals(correct)) {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.w_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        addDataBase(finalI1);
                        if (correct.equals("A")) {
                            answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                            answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("B")) {
                            answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                            answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("D")) {
                            answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                            answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("E")) {
                            answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                            answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        }
                    }
                    tvTipsYourChoose.setText("C");
                    bottomHide.setVisibility(View.VISIBLE);
                }
            });
            linearAnswerD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("D".equals(correct)) {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        bottomHide.setVisibility(View.VISIBLE);
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.w_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        addDataBase(finalI1);
                        if (correct.equals("A")) {
                            answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                            answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("B")) {
                            answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                            answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("C")) {
                            answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                            answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("E")) {
                            answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                            answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        }
                    }
                    tvTipsYourChoose.setText("D");
                    bottomHide.setVisibility(View.VISIBLE);
                }
            });
            linearAnswerE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("E".equals(correct)) {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        bottomHide.setVisibility(View.VISIBLE);
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.w_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        addDataBase(finalI1);
                        if (correct.equals("A")) {
                            answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                            answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("B")) {
                            answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                            answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("C")) {
                            answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                            answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        } else if (correct.equals("D")) {
                            answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                            answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        }
                    }
                    tvTipsYourChoose.setText("E");
                    bottomHide.setVisibility(View.VISIBLE);
                }
            });
            final int finalI = i;
            btnLookOtherNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommentParcel commentParcel = new CommentParcel();
                    String title=practiceList.get(finalI).getName();
                    int topic_id=practiceList.get(finalI).getId();
                    commentParcel.setId(topic_id);
                    commentParcel.setTitle(title);
                    Intent intent = new Intent(WrongChapterPracticeActivity.this, OtherCommentActivity.class);
                    intent.putExtra("commentParcel", commentParcel);
                    startActivity(intent);
                }
            });
            viewsList.add(view);
        }
    }
    public void addDataBase(int finalI1){
        PracticeData practiceData=new PracticeData();
        practiceData.setId(practiceList.get(finalI1).getId());
        practiceData.setName(practiceList.get(finalI1).getName());
        practiceData.setA(practiceList.get(finalI1).getA());
        practiceData.setB(practiceList.get(finalI1).getB());
        practiceData.setC(practiceList.get(finalI1).getC());
        practiceData.setD(practiceList.get(finalI1).getD());
        practiceData.setE(practiceList.get(finalI1).getE());
        practiceData.setCorrect(practiceList.get(finalI1).getCorrect());
        practiceData.setAnalysis(practiceList.get(finalI1).getAnalysis());
        practiceData.save();
    }
}
