package com.botian.yihu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.adapter.NewsListAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.NewsLable;
import com.botian.yihu.beans.NewsList;
import com.botian.yihu.beans.NewsZip;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
    private List<String> mVals = new ArrayList<>();
    private List<Integer> mVals2 = new ArrayList<>();
    private SharedPreferences pref;
    @BindView(R.id.toolbar_news)
    Toolbar toolbarNews;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    int current_page;//当前分页
    int last_page;//总分页
    String filter2 ;
    String filter;
    String filter3;
    String typeid;
    ObserverOnNextListener<NewsList> listener;
    ObserverOnNextListener<NewsZip> listener2;
    List<NewsList.DataBeanX.DataBean> listData = new ArrayList<>();
    List<NewsZip> listData2 = new ArrayList<>();
    View header;
    int no;//科目id
    int no2;//科目id

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
        filter = "mid,eq," + no;
        filter3 = "mids,eq," + no2;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        header = LayoutInflater.from(getContext()).inflate(R.layout.header_news, null);
        listener2 = new ObserverOnNextListener<NewsZip>() {
            @Override
            public void onNext(NewsZip data) {
                setLable(data.getData());
                setAdapter2(data.getData2());
                //禁用下拉刷新和加载更多功能
                recyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        ApiMethods.getNewsLable(new ProgressObserver<NewsList>(getContext(), listener2), filter, filter,(RxAppCompatActivity) getActivity());
        listener = new ObserverOnNextListener<NewsList>() {
            @Override
            public void onNext(NewsList data) {
                setAdapter(data);
                //禁用下拉刷新和加载更多功能
                recyclerView.setPullRefreshEnabled(false);
                //commentRecyclerView.setLoadingMoreEnabled(false);
            }
        };
        //ApiMethods.getNewsList(new ProgressObserver<NewsList>(getContext(),listener), filter1, "1", "20", (RxAppCompatActivity) getActivity());
    }

    public void setAdapter(NewsList data) {
        current_page = data.getData().getCurrent_page();
        last_page = data.getData().getLast_page();
        listData.addAll(data.getData().getData());
        if (current_page == 1) {//判断是否初次加载
            //String a = data.getData().getTotal() + "";
            //comment.setText(a);
            //title.setText(commentParcel.getTitle());
            recyclerView.addHeaderView(header);
            NewsListAdapter adapter = new NewsListAdapter(getActivity(), listData);
            recyclerView.setAdapter(adapter);
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
                    ApiMethods.getNewsList(new ProgressObserver<NewsList>(getContext(), listener), filter,filter, filter3,current_page + 1 + "", "20", (RxAppCompatActivity) getActivity());
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
    }

    public void setAdapter2(NewsList.DataBeanX data) {
        current_page = data.getCurrent_page();
        last_page = data.getLast_page();
        listData.addAll(data.getData());
        if (current_page == 1) {//判断是否初次加载
            recyclerView.addHeaderView(header);
            NewsListAdapter adapter = new NewsListAdapter(getActivity(), listData);
            recyclerView.setAdapter(adapter);
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
                    ApiMethods.getNewsList(new ProgressObserver<NewsList>(getContext(), listener), filter,filter, filter3,current_page + 1 + "", "20", (RxAppCompatActivity) getActivity());
                }
                //ApiMethods.getComment(new ProgressObserver<OtherCommentBean>(OtherCommentActivity.this, listener), commentParcel.getId() + "",current_page+1+"","20");
                //commentRecyclerView.loadMoreComplete();
            }
        });
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

    private void setFlowlayout() {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        final TagFlowLayout mFlowLayout = header.findViewById(R.id.id_flowlayout);
        TagAdapter tagAdapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_flowlayout_news,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        tagAdapter.setSelectedList(0);
        mFlowLayout.setAdapter(tagAdapter);
        //点击事件
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                return true;
            }
        });
    }

    public void setLable(List<NewsLable.DataBean> data) {
        typeid=data.get(0).getId()+"";
        filter2 = "typeid,eq," + typeid;
        for (int i = 0; i < data.size(); i++) {
            mVals.add(data.get(i).getTypename());
            mVals2.add(data.get(i).getId());
        }
        setFlowlayout();
    }
}
