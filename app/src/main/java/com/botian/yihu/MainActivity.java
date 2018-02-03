package com.botian.yihu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private VideoFragment mVideoFragment;
    private MineFragment mMineFragment;
    private NewsFragment mNewsFragment;
    private PracticeFragment mPracticeFragment;
    private LiveFragment mLiveFragment;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.setInActiveColor(R.color.Main_text_unclick);
        bottomNavigationBar.setActiveColor(R.color.Main_text_click);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_1_active, "练习").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.nav_1)))
                .addItem(new BottomNavigationItem(R.drawable.nav_2_active, "课程").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.nav_2)))
                .addItem(new BottomNavigationItem(R.drawable.main_live_focus, "直播").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.main_live)))
                .addItem(new BottomNavigationItem(R.drawable.nav_3_active, "资讯").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.nav_3)))
                .addItem(new BottomNavigationItem(R.drawable.nav_4_active, "我的").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.nav_4)))
                .setFirstSelectedPosition(0)
                .initialise();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }
    /**
     * 设置默认的
     */

    private void setDefaultFragment() {

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mPracticeFragment = PracticeFragment.newInstance("题库");
        transaction.add(R.id.layFrame, mPracticeFragment);
        transaction.commit();
    }
    @Override
    public void onTabSelected(int position) {

        //开启事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        //fragment 用 add + show + hide 方式
        //只有第一次切换会创建fragment，再次切换不创建

        switch (position) {
            case 0:
                if (mPracticeFragment == null) {
                    mPracticeFragment = PracticeFragment.newInstance("练习");
                    transaction.add(R.id.layFrame, mPracticeFragment);
                }else{
                    transaction.show(mPracticeFragment);
                }
                break;
            case 1:
                if (mVideoFragment == null) {
                    mVideoFragment = VideoFragment.newInstance("课程");
                    transaction.add(R.id.layFrame, mVideoFragment);
                }else{
                    transaction.show(mVideoFragment);
                }
                break;
            case 2:
                if (mLiveFragment == null) {
                    mLiveFragment = LiveFragment.newInstance("直播");
                    transaction.add(R.id.layFrame, mLiveFragment);
                }else{
                    transaction.show(mLiveFragment);
                }
                break;
            case 3:
                if (mNewsFragment == null) {
                    mNewsFragment = NewsFragment.newInstance("资讯");
                    transaction.add(R.id.layFrame, mNewsFragment);
                }else{
                    transaction.show(mNewsFragment);
                }
                break;
            case 4:
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance("我的");
                    transaction.add(R.id.layFrame, mMineFragment);
                }else{
                    transaction.show(mMineFragment);
                }
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if (mPracticeFragment != null){
            transaction.hide(mPracticeFragment);
        }
        if (mVideoFragment != null){
            transaction.hide(mVideoFragment);
        }
        if (mLiveFragment != null){
            transaction.hide(mLiveFragment);
        }
        if (mNewsFragment != null){
            transaction.hide(mNewsFragment);
        }
        if (mMineFragment != null){
            transaction.hide(mMineFragment);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}