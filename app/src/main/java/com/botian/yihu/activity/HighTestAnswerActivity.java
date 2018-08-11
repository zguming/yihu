package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.botian.yihu.R;
import com.botian.yihu.adapter.MyFragmentPagerAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.HighTest;
import com.botian.yihu.beans.KaoQianNormal;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.TopicCardEvent1;
import com.botian.yihu.fragment.HighTestPracticeFragment;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HighTestAnswerActivity extends RxAppCompatActivity {
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
    private ACache mCache;
    private UserInfo userInfo;
    String subjectNo;
    String typeid;
    private String hostUrl = "http://btsc.botian120.com";
    private List<HighTest.DataBean> practiceList = new ArrayList<>();
    private int already = 0;//已答总数
    private int correct1 = 0;//答对总数
    private ArrayList<Integer> topicCard = new ArrayList<>();//答对0，答错1，当前题2
    private int position = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();//无背题
    private MyFragmentPagerAdapter mAdapter;
    FragmentManager fm;
    private String correct;
    private String corrects;
    private String wrong;
    private String wrongs;
    private int c1 = 0;
    private int c2 = 0;
    private int w1 = 0;
    private int w2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaoqian);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        typeid = intent.getStringExtra("typeid");
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        ObserverOnNextListener<HighTest> listener = new ObserverOnNextListener<HighTest>() {
            @Override
            public void onNext(HighTest data) {
                practiceList = data.getData();
                initView();
                lnTopicCard.setClickable(true);
                for (int i = 0; i < practiceList.size(); i++) {
                    topicCard.add(6);//6是初始默认数字，无意义
                }

            }
        };
        ApiMethods.getHighTest(new ProgressObserver<HighTest>(HighTestAnswerActivity.this, listener), "mid,eq,"+ SubjectUtil.getSubjectNo(),"mids,eq,"+SubjectUtil.getSubjectNo2(), this);

        initView1();
    }

    public void initView() {
        for (int i = 0; i < practiceList.size(); i++) {
            Fragment fragment = HighTestPracticeFragment.newInstance(practiceList.get(i), i, practiceList.size());
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


    @OnClick({R.id.back, R.id.ib_left, R.id.ln_topic_card,  R.id.ib_right})
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
        viewPager.setCurrentItem(messageEvent.getMessage(), false);
    }

    public void test(int i, int j) {
        //AnswerFragment会调用此方法

        topicCard.set(i, j);
        if (j == 0) {
            correct1 = correct1 + 1;



        } else if (j == 1) {


        }


    }

    public void test2() {
        //AnswerFragment会调用此方法
        already = already + 1;


    }

}
