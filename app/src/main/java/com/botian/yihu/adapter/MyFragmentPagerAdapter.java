package com.botian.yihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentPair;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragmentPair) {
        super(fm);
        this.mFragmentPair = mFragmentPair;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentPair.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentPair.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //如果注释这行，那么不管怎么切换，page都不会被销毁
        //super.destroyItem(container, position, object);
    }

}
