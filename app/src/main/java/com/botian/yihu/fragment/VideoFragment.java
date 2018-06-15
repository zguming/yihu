package com.botian.yihu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botian.yihu.GlideImageLoader;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.VideoClassAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.VideoClass;
import com.botian.yihu.beans.VideoClassZip;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by WangChang on 2016/5/15.
 */
//视频课程
public class VideoFragment extends RxFragment {
    View view;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private SharedPreferences pref;
    View header;
    private List<String> images = new ArrayList<>();
    private String filter = "mid,eq,1";//首页轮播图mid==1
    Banner banner;
    private List<VideoClass.DataBean> data3;
    VideoClassAdapter adapter;
    String mid2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        header = LayoutInflater.from(getActivity()).inflate(R.layout.header_video_class, null);
        banner = header.findViewById(R.id.banner);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref = this.getActivity().getSharedPreferences("subjectSelectData", Context.MODE_PRIVATE);
        getData();

    }

    public void getData() {

        mid2 = pref.getInt("subjectNo", 1) + "";
        ObserverOnNextListener<VideoClassZip> listener = new ObserverOnNextListener<VideoClassZip>() {
            @Override
            public void onNext(VideoClassZip data) {
                //禁用下拉刷新和加载更多功能
                data3=data.getData();
                recyclerView.setPullRefreshEnabled(false);
                recyclerView.setLoadingMoreEnabled(false);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addHeaderView(header);
                adapter = new VideoClassAdapter(getActivity(), data3);
                recyclerView.setAdapter(adapter);
                for (int i = 0; i < data.getData2().size(); i++) {
                    images.add("http://btsc.botian120.com" + data.getData2().get(i).getLitpic());
                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(images);
                banner.setDelayTime(4000);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
        };
        ApiMethods.getVideoZip(new ProgressObserver<VideoClassZip>(getActivity(), listener), filter, "mid,eq,"+mid2, (RxAppCompatActivity) getActivity());
    }

    public static VideoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
// TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            final String mid3 = pref.getInt("subjectNo", 1) + "";
            if(!mid3.equals(mid2)){
                ObserverOnNextListener<VideoClassZip> listener2 = new ObserverOnNextListener<VideoClassZip>() {
                    @Override
                    public void onNext(VideoClassZip data) {
                        //禁用下拉刷新和加载更多功能
                        data3.clear();
                        data3.addAll(data.getData());
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < data.getData2().size(); i++) {
                            images.add("http://btsc.botian120.com" + data.getData2().get(i).getLitpic());
                        }
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        banner.setImages(images);
                        banner.setDelayTime(4000);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                        mid2=mid3;
                    }
                };
                String filter2="mid,eq,"+mid3;
                ApiMethods.getVideoZip(new ProgressObserver<VideoClassZip>(getActivity(), listener2), filter, filter2, (RxAppCompatActivity) getActivity());
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
