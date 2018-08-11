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
import com.botian.yihu.adapter.VideoClassAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.Live;
import com.botian.yihu.beans.VideoClassZip;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.youth.banner.BannerConfig;

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
        ObserverOnNextListener<Live> listener = new ObserverOnNextListener<Live>() {
            @Override
            public void onNext(Live data) {
                //禁用下拉刷新和加载更多功能
                recyclerView.setPullRefreshEnabled(false);
                recyclerView.setLoadingMoreEnabled(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                LiveAdapter adapter = new LiveAdapter(getActivity(), data.getData());
                recyclerView.setAdapter(adapter);

            }
        };
        ApiMethods.getLive(new ProgressObserver<Live>(getActivity(), listener),  (RxAppCompatActivity) getActivity());
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


}

