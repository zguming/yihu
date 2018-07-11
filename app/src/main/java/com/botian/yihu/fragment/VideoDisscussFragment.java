package com.botian.yihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.MyObserver;
import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.activity.SendVideoCommentActivity;
import com.botian.yihu.adapter.VideoCommentAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.VideoComment;
import com.botian.yihu.eventbus.TopicCardEvent;
import com.botian.yihu.eventbus.VideoCommentEvent;
import com.botian.yihu.util.ACache;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
//资讯
public class VideoDisscussFragment extends Fragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    View header;
    TextView text;
    ObserverOnNextListener<VideoComment> listener;
    int current_page;//当前分页
    int last_page;//总分页
    List<VideoComment.DataBeanX.DataBean> listData = new ArrayList<>();
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    String mid;//视频id
    private int re=0;
    private ACache mCache;
    private UserInfo userInfo;
    VideoCommentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        EventBus.getDefault().register(this);
        mCache = ACache.get(getActivity());
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        mid = bundle.getString("ARGS");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        header = LayoutInflater.from(getActivity()).inflate(R.layout.header_video_comment, null);
        text = header.findViewById(R.id.num);
        listener = new ObserverOnNextListener<VideoComment>() {
            @Override
            public void onNext(VideoComment data) {
                setAdapter(data.getData());
                //禁用下拉刷新和加载更多功能
                recyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        ApiMethods.getVideoCommentList(new ProgressObserver<VideoComment>(getContext(), listener), "mid,eq,"+mid, "1", "30", (RxAppCompatActivity) getActivity());

    }

    public void setAdapter(VideoComment.DataBeanX data) {
        current_page = data.getCurrent_page();
        last_page = data.getLast_page();
        listData.addAll(data.getData());
        if (current_page == 1) {//判断是否初次加载
            String a = "评价详情（" + data.getTotal() + "条评价）";
            text.setText(a);
            if (re==1){
                adapter.notifyDataSetChanged();
            }else {
                recyclerView.addHeaderView(header);
                adapter = new VideoCommentAdapter(getContext(), (RxAppCompatActivity) getActivity(), listData);
            recyclerView.setAdapter(adapter);}
        } else {
            recyclerView.loadMoreComplete();
        }
        if (current_page == last_page) {
            recyclerView.setLoadingMoreEnabled(false);
        }
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
            }

            @Override
            public void onLoadMore() {
                // load more data here
                if (current_page == last_page) {
                } else {
                    ApiMethods.getVideoCommentList2(new MyObserver<VideoComment>(listener), "mid,eq,1", current_page + 1 + "", "30", (RxAppCompatActivity) getActivity());
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
    }

    public static VideoDisscussFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoDisscussFragment fragment = new VideoDisscussFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @OnClick(R.id.rl_comment)
    public void onViewClicked() {
        if (userInfo==null){
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getActivity(), SendVideoCommentActivity.class);
            intent.putExtra("mid",mid);
            startActivity(intent);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VideoCommentEvent messageEvent) {
        listData.clear();
        re=1;
        ApiMethods.getVideoCommentList(new ProgressObserver<VideoComment>(getActivity(), listener), "mid,eq,"+mid, "1", "30", (RxAppCompatActivity) getActivity());
    }


}
