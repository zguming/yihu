package com.botian.yihu.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.Version;
import com.botian.yihu.fragment.LiveFragment;
import com.botian.yihu.fragment.NewsFragment;
import com.botian.yihu.fragment.PracticeFragment;
import com.botian.yihu.fragment.UserFragment;
import com.botian.yihu.fragment.VideoFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

//主界面
public class MainActivity extends RxAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private VideoFragment mVideoFragment;
    private UserFragment mUserFragment;
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
        ObserverOnNextListener<Version> listener = new ObserverOnNextListener<Version>() {
            @Override
            public void onNext(Version data) {
                String versionCode="0";
                PackageManager manager = getPackageManager();//获取包管理器
                try {
                    //通过当前的包名获取包的信息
                    PackageInfo info = manager.getPackageInfo(getPackageName(),0);//获取包对象信息
                    versionCode=info.versionCode+"";
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
              String version = data.getData().get(0).getName();
                if (versionCode.equals(version)){

                }
            }
        };
        ApiMethods.getVersion(new MyObserver<Version>( listener),this);
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
                if (mUserFragment == null) {
                    mUserFragment = UserFragment.newInstance("我的");
                    transaction.add(R.id.layFrame, mUserFragment);
                }else{
                    transaction.show(mUserFragment);
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
        if (mUserFragment != null){
            transaction.hide(mUserFragment);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    //再按一次退出程序
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}