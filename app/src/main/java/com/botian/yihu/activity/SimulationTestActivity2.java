package com.botian.yihu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.MyFragmentPagerAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.MoniTopic;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.TopicCardEvent1;
import com.botian.yihu.fragment.MoniPracticeFragment;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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
    @BindView(R.id.ib_right)
    ImageButton ibRight;
    @BindView(R.id.ln_topic_card)
    LinearLayout lnTopicCard;
    @BindView(R.id.ln_topic_card1)
    LinearLayout lnTopicCard1;
    @BindView(R.id.timer)
    TextView timer1;
    private ACache mCache;
    private UserInfo userInfo;
    String subjectNo;
    String typeid;
    private String hostUrl = "http://btsc.botian120.com";
    private List<MoniTopic.DataBean> practiceList = new ArrayList<>();
    private int already = 0;//已答总数
    private int correct1 = 0;//答对总数
    private ArrayList<Integer> topicCard = new ArrayList<>();//答对0，答错1，当前题2
    private int position = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();//无背题
    private MyFragmentPagerAdapter mAdapter;
    FragmentManager fm;
    private String correct;
    private String time11;
    private String score11;
    private String corrects;
    private String wrong;
    private String wrongs;
    private int c1 = 0;
    private int c2 = 0;
    private int w1 = 0;
    private int w2 = 0;
    private long time;
    int score23;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_test2);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        subjectNo = intent.getStringExtra("mid");
        typeid = intent.getStringExtra("typeid");
        time11 = intent.getStringExtra("time");
        score11 = intent.getStringExtra("score");
        score23= Integer.parseInt(score11);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<MoniTopic> listener = new ObserverOnNextListener<MoniTopic>() {
            @Override
            public void onNext(MoniTopic data) {
                practiceList = data.getData();
                initView();
                lnTopicCard.setClickable(true);
                for (int i = 0; i < practiceList.size(); i++) {
                    topicCard.add(6);//6是初始默认数字，无意义
                }
                /** 倒计时60秒，一次1秒 */
                 timer = new CountDownTimer(100*60 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // TODO Auto-generated method stub
                        //text.setText("还剩"+millisUntilFinished/1000+"秒");
                        time = 6000 - (millisUntilFinished / 1000);
                        String timeStr = getTime(millisUntilFinished);
                        timer1.setVisibility(View.INVISIBLE);
                        timer1.setText(timeStr);

                    }

                    @Override
                    public void onFinish() {
                        //timer1.setText("时间用完");
                        lnTopicCard1.setClickable(false);
                        Double score1=(double)correct1/120;
                        Double score2=score1*380;
                        BigDecimal b = new BigDecimal(score2);
                        score2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        String score = score2 + "";
                        showScore(score,"时间用完");
                    }
                }.start();
            }
        };
        ApiMethods.getMoniTestTopic(new ProgressObserver<MoniTopic>(this, listener), "mids,eq," + subjectNo, "typeid,eq," + typeid, this);
        initView1();
    }

    public static String getTime(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(ms);
        System.out.println(hms);
        return hms;
    }

    public void initView() {
        for (int i = 0; i < practiceList.size(); i++) {
            Fragment fragment = MoniPracticeFragment.newInstance(practiceList.get(i), i, practiceList.size());
            fragmentlist.add(fragment);
        }
        fm = getSupportFragmentManager();
        mAdapter = new MyFragmentPagerAdapter(fm, fragmentlist);
        viewPager.setAdapter(mAdapter);
    }

    public void initView1() {
        lnTopicCard.setClickable(false);
        bottomTab.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.back, R.id.ib_left, R.id.ln_topic_card, R.id.ln_topic_card1, R.id.ib_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_left:
                position = viewPager.getCurrentItem();
                if (position > 0) {
                    viewPager.setCurrentItem(position - 1);
                    position = position - 1;
                }
                break;
            case R.id.ln_topic_card:
                Intent intent = new Intent(this, TopicCardActivity.class);
                intent.putIntegerArrayListExtra("topicCard", topicCard);
                intent.putExtra("already", already);
                intent.putExtra("correct", correct1);
                intent.putExtra("switch", "1");
                startActivity(intent);
                break;
            case R.id.ln_topic_card1:
                lnTopicCard1.setClickable(false);
                timer.cancel();
                // 创建构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 设置参数
                builder.setTitle("提交")
                        .setMessage("是否确定交卷")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                //Toast.makeText(MainActivity.this, "恭喜你答对了", 0)
                                // .show();
                                ObserverOnNextListener<No> listener133 = new ObserverOnNextListener<No>() {
                                    @Override
                                    public void onNext(No data) {


                                    }
                                };
                                Double score1=(double)correct1/ 120;
                                Double score2=score1*380;
                                BigDecimal b = new BigDecimal(score2);
                                score2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                String score = score2 + "";
                                ApiMethods.postMoni(new MyObserver<No>(listener133), userInfo.getId() + "", time + "", score, correct, wrong, corrects, wrongs, SimulationTestActivity2.this);
                                showScore(score,"成绩");
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(MainActivity.this, "一点也不老实", 0)
                        // .show();
                    }
                });
                builder.create().show();
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

    public void showScore(String score,String msg) {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置参数
        builder.setTitle(msg)
                .setMessage("总分120分"+"\n"+"做对了"+correct1+"道题"+"\n"+"您的考试成绩为："+correct1+"分")
                .setPositiveButton("查看详细", new DialogInterface.OnClickListener() {// 积极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        lnTopicCard.performClick();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this, "一点也不老实", 0)
                // .show();
            }
        });

        builder.create().show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TopicCardEvent1 messageEvent) {
        viewPager.setCurrentItem(messageEvent.getMessage(), false);
    }

    public void test(int i, int j) {
        //AnswerFragment会调用此方法

        topicCard.set(i, j);
        if (j == 0) {
            correct1 = correct1 + 1;
            if (practiceList.get(i).getTitlecl()!=null) {
                if (c1 == 0) {
                    correct = correct + practiceList.get(i).getId();
                    c1 = 1;
                } else {
                    correct = "," + correct + practiceList.get(i).getId();
                }
            } else {
                if (c2 == 0) {
                    corrects = corrects + practiceList.get(i).getId();
                    c2 = 1;
                } else {
                    corrects = "," + corrects + practiceList.get(i).getId();
                }
            }

        } else if (j == 1) {
            if (practiceList.get(i).getTitlecl() != null) {
                if (w1 == 0) {
                    wrong = wrong + practiceList.get(i).getId();
                    w1 = 1;
                } else {
                    wrong = "," + wrong + practiceList.get(i).getId();
                }
            } else {
                if (w2 == 0) {
                    wrongs = wrongs + practiceList.get(i).getId();
                    w2 = 1;
                } else {
                    wrongs = "," + wrongs + practiceList.get(i).getId();
                }
            }
        }


    }

    public void test2() {
        //AnswerFragment会调用此方法
        already = already + 1;


    }

}
