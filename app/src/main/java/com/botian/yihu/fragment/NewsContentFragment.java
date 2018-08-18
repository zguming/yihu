package com.botian.yihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.R;
import com.botian.yihu.adapter.NewsListAdapter;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.NewsList;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsContentFragment extends Fragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private View view;
    private int id;
    private String filter;
    private String filter2;
    protected boolean isCreate = false;
    private ObserverOnNextListener<NewsList> listener;
    private int current_page;
    private int last_page;
    private List<NewsList.DataBeanX.DataBean> listData = new ArrayList<>();
    private NewsListAdapter adapter;
    private int init = 0;
    private int init2 = 0;//判断是否为刷新

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCreate = true;
        if (getUserVisibleHint()) {
            //加载数据相当于Fragment的onPause，这样就能看到第一页的数据了！
            lazyLoad();
            init = 1;
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            if (init == 0) {
                //可见时加载数据相当于Fragment的onResume
                lazyLoad();
                init = 1;
            } else {
                //不可见时不加载数据

            }
        }
    }

    public void lazyLoad() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            id = bundle.getInt("id");
            filter = bundle.getString("filter");
            filter2 = bundle.getString("filter2");
        }
        listener = new ObserverOnNextListener<NewsList>() {
            @Override
            public void onNext(NewsList newsList) {
                setAdapter(newsList);
            }
        };
        ApiMethods.getNewsList(new MyObserver<NewsList>(listener), filter, filter2, "typeid,eq," + id, "1", "10", (RxAppCompatActivity) getActivity());

    }

    public void setAdapter(NewsList data) {
        current_page = data.getData().getCurrent_page();
        last_page = data.getData().getLast_page();
        listData.addAll(data.getData().getData());
        if (init2 == 0) {
            if (current_page == 1) {//判断是否初次加载
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new NewsListAdapter(getActivity(), listData);
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.loadMoreComplete();
            }
        }else{
            init2=0;
            recyclerView.setLoadingMoreEnabled(true);
            adapter.notifyDataSetChanged();
            recyclerView.refreshComplete();

        }

        if (current_page == last_page)

        {
            recyclerView.setLoadingMoreEnabled(false);
        }
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener()

        {
            @Override
            public void onRefresh() {
                //refresh data here
                init2=1;
                listData.clear();
                ApiMethods.getNewsList(new MyObserver<NewsList>(listener), filter, filter2, "typeid,eq," + id, "1", "10", (RxAppCompatActivity) getActivity());

            }

            @Override
            public void onLoadMore() {
                // load more data here
                if (current_page == last_page) {
                } else {
                    ApiMethods.getNewsList(new MyObserver<NewsList>(listener), filter, filter2, "typeid,eq," + id, current_page + 1 + "", "10", (RxAppCompatActivity) getActivity());
                }
            }
        });
    }

    public NewsContentFragment newInstance(int id, String filter, String filter2) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("filter", filter);
        bundle.putString("filter2", filter2);
        NewsContentFragment Fm = new NewsContentFragment();
        Fm.setArguments(bundle);
        return Fm;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}