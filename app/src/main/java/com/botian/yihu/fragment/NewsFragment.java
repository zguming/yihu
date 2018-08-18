package com.botian.yihu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.MyView.MyViewPager;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.adapter.TabFragmentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.NewsLable;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//资讯
public class NewsFragment extends Fragment {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    private SharedPreferences pref;
    Unbinder unbinder;
    String filter;
    String filter2;
    ObserverOnNextListener<NewsLable> listener;
    int no;//科目id
    int no2;//科目id
    public List<String> strings = new ArrayList<>();
    public List<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref = this.getActivity().getSharedPreferences("subjectSelectData", Context.MODE_PRIVATE);
        no = pref.getInt("subjectNo", 1);
        no2 = pref.getInt("subjectNo2", 2);
        filter = "mid,eq," + no;
        filter2 = "mids,eq," + no2;

        listener = new ObserverOnNextListener<NewsLable>() {
            @Override
            public void onNext(NewsLable newsLable) {
                initdata(newsLable);
            }
        };
        ApiMethods.getNewsLable(new MyObserver<NewsLable>(listener), filter, filter2, (RxAppCompatActivity) getActivity());

    }
    public void initView(){
        viewpager.setAdapter(new TabFragmentAdapter(fragments,strings,
                getActivity().getSupportFragmentManager(),getContext()));
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabTextColors(getResources().getColor(R.color.textColorBlack)
                ,getResources().getColor(R.color.blue));

    }

    public void initdata(NewsLable newsLable) {
        for (int i = 0; i < newsLable.getData().size(); i++) {
            NewsContentFragment newsFragment = new NewsContentFragment().newInstance(newsLable.getData().get(i).getId(),filter,filter2);
            fragments.add(newsFragment);
            strings.add(newsLable.getData().get(i).getTypename());
        }
        initView();
    }

    public static NewsFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
