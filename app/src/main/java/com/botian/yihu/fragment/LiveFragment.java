package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botian.yihu.R;
import com.botian.yihu.adapter.LiveAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.Live;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.SubjectUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
//直播
public class LiveFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private String mid6;
    private List<Live.DataBean> list = new ArrayList<>();
    ObserverOnNextListener<Live> listener;
    private int init = 0;
    private LiveAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mid6 = SubjectUtil.getSubjectNo2() + "";
        listener = new ObserverOnNextListener<Live>() {
            @Override
            public void onNext(Live data) {
                list.addAll(data.getData());
                //禁用下拉刷新和加载更多功能
                if (init == 0) {
                    recyclerView.setPullRefreshEnabled(false);
                    recyclerView.setLoadingMoreEnabled(false);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new LiveAdapter(getActivity(), list);
                    recyclerView.setAdapter(adapter);
                    init=1;
                } else {
                    adapter.notifyDataSetChanged();
                }

            }
        };
        ApiMethods.getLive(new ProgressObserver<Live>(getActivity(), listener), (RxAppCompatActivity) getActivity());
    }

    public static LiveFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
// TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            final String mid4 = SubjectUtil.getSubjectNo2() + "";
            if (!mid4.equals(mid6)) {
                list.clear();
                ApiMethods.getLive(new ProgressObserver<Live>(getActivity(), listener), (RxAppCompatActivity) getActivity());
                mid6=mid4;

            }

        }
    }


}

