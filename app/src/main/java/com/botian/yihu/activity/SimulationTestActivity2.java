package com.botian.yihu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.MyPagerAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTopic;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.TopicCardEvent;
import com.botian.yihu.eventbus.TopicCardEvent1;
import com.botian.yihu.util.ACache;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimulationTestActivity2 extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.ib_left)
    ImageButton ibLeft;
    @BindView(R.id.tv_topic_card)
    TextView tvTopicCard;
    @BindView(R.id.ib_right)
    ImageButton ibRight;
    private ACache mCache;
    private UserInfo userInfo;
    String subjectNo;
    String typeid;
    private ArrayList<View> viewsList;
    private MyPagerAdapter mAdapter;
    private String hostUrl = "http://btsc.botian120.com";
    private List<MoniTopic.DataBean> practiceList = new ArrayList<>();
    private int already=0;//已答总数
    private int correct1=0;//答对总数
    private ArrayList<Integer> topicCard =new ArrayList<>();//答对0，答错1，当前题2
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_test2);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        subjectNo = intent.getStringExtra("mid");
        typeid = intent.getStringExtra("typeid");
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<MoniTopic> listener = new ObserverOnNextListener<MoniTopic>() {
            @Override
            public void onNext(MoniTopic data) {
                practiceList = data.getData();
                viewsList = new ArrayList<View>();
                initView();
                mAdapter = new MyPagerAdapter(viewsList);
                viewPager.setAdapter(mAdapter);
                tvTopicCard.setClickable(true);
                for (int i=0;i<practiceList.size();i++){
                    topicCard.add(6);//6是初始默认数字，无意义
                }
            }
        };
        ApiMethods.getMoniTestTopic(new ProgressObserver<MoniTopic>(this, listener), "mid,eq," + subjectNo, "typeid,eq," + typeid, this);
        initView1();
    }

    public void initView1() {
        Drawable topicCard = getResources().getDrawable(R.drawable.ic_topic_card_normal);
        topicCard.setBounds(0, 0, 100, 100);
        tvTopicCard.setCompoundDrawables(null, topicCard, null, null);
        tvTopicCard.setClickable(false);
        bottomTab.setVisibility(View.VISIBLE);
    }

    public void initView() {
        for (int i = 0; i < practiceList.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_answer, null, false);
            TextView cailiao = view.findViewById(R.id.cailiao);
            if (practiceList.get(i).getTitlecl() != null) {
                cailiao.setText(practiceList.get(i).getTitlecl());
                cailiao.setVisibility(View.VISIBLE);
            }
            TextView timu = view.findViewById(R.id.timu);
            ImageView imageView = view.findViewById(R.id.imageview);
            TextView current = view.findViewById(R.id.current_num);
            String currentNum = i + 1 + "";//当前数目
            current.setText(currentNum);
            TextView total = view.findViewById(R.id.total_num);
            String totalNum = "/" + practiceList.size();//总数目
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
            final TextView btnNoteEdit = view.findViewById(R.id.btnNoteEdit);
            final TextView noteContent = view.findViewById(R.id.noteContent);
            final TextView btnFeedbackError = view.findViewById(R.id.btn_feedback_error);
            TextView check = view.findViewById(R.id.check);
            TextView analyse = view.findViewById(R.id.analyseinfo);
            timu.setText(practiceList.get(i).getTitle());
            answerTextA.setText(practiceList.get(i).getA());
            answerTextB.setText(practiceList.get(i).getB());
            answerTextC.setText(practiceList.get(i).getC());
            answerTextD.setText(practiceList.get(i).getD());
            answerTextE.setText(practiceList.get(i).getE());
            check.setText(practiceList.get(i).getCorrect());
            analyse.setText(practiceList.get(i).getAnalysis());
            final String correct = practiceList.get(i).getCorrect();
            String picUrl = hostUrl + practiceList.get(i).getLitpic();
            Glide.with(this)
                    .load(picUrl)
                    .into(imageView);
            final int finalI1 = i;
            linearAnswerA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    already=already+1;
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("A".equals(correct)) {
                        correct1=correct1+1;
                        topicCard.set(finalI1,0);
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.r_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        topicCard.set(finalI1,1);
                        answerA.setImageDrawable(getResources().getDrawable(R.drawable.w_1));
                        answerTextA.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        //addDataBase(finalI1);
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
                    already=already+1;
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("B".equals(correct)) {
                        correct1=correct1+1;
                        topicCard.set(finalI1,0);
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.r_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        topicCard.set(finalI1,1);
                        answerB.setImageDrawable(getResources().getDrawable(R.drawable.w_2));
                        answerTextB.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        //addDataBase(finalI1);
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
                    already=already+1;
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("C".equals(correct)) {
                        correct1=correct1+1;
                        topicCard.set(finalI1,0);
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.r_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        topicCard.set(finalI1,1);
                        answerC.setImageDrawable(getResources().getDrawable(R.drawable.w_3));
                        answerTextC.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        //addDataBase(finalI1);
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
                    already=already+1;
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("D".equals(correct)) {
                        correct1=correct1+1;
                        topicCard.set(finalI1,0);
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.r_4));
                        bottomHide.setVisibility(View.VISIBLE);
                        answerTextD.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        topicCard.set(finalI1,1);
                        answerD.setImageDrawable(getResources().getDrawable(R.drawable.w_4));
                        answerTextD.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        //addDataBase(finalI1);
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
                    already=already+1;
                    linearAnswerA.setClickable(false);
                    linearAnswerB.setClickable(false);
                    linearAnswerC.setClickable(false);
                    linearAnswerD.setClickable(false);
                    linearAnswerE.setClickable(false);
                    if ("E".equals(correct)) {
                        correct1=correct1+1;
                        topicCard.set(finalI1,0);
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.r_5));
                        bottomHide.setVisibility(View.VISIBLE);
                        answerTextE.setTextColor(getResources().getColor(R.color.correct));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.correct));
                    } else {
                        topicCard.set(finalI1,1);
                        answerE.setImageDrawable(getResources().getDrawable(R.drawable.w_5));
                        answerTextE.setTextColor(getResources().getColor(R.color.false1));
                        tvTipsYourChoose.setTextColor(getResources().getColor(R.color.false1));
                        //把错题添加到数据库
                        //addDataBase(finalI1);
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

            viewsList.add(view);
        }
    }



    @OnClick({R.id.back,R.id.ib_left, R.id.tv_topic_card, R.id.ib_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_left:
                position = viewPager.getCurrentItem();
                if (position > 0) {
                    viewPager.setCurrentItem(position - 1);
                    position = position - 1;
                }
                break;
            case R.id.tv_topic_card:
                Intent intent = new Intent(this, TopicCardActivity.class);
                intent.putIntegerArrayListExtra("topicCard",topicCard);
                intent.putExtra("already",already);
                intent.putExtra("correct",correct1);
                intent.putExtra("switch","1");
                startActivity(intent);
                break;
            case R.id.ib_right:
                position = viewPager.getCurrentItem();
                if (position < practiceList.size() - 1) {
                    viewPager.setCurrentItem(position + 1);
                    position = position + 1;
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TopicCardEvent1 messageEvent) {
        viewPager.setCurrentItem(messageEvent.getMessage(),false);
    }
}
