package com.botian.yihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.activity.PlayVideoActivity;
import com.botian.yihu.androidTreeListView.SelectableHeaderHolder;
import com.botian.yihu.androidTreeListView.SelectableItemHolder;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.data.VideoListBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

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
    @BindView(R.id.container)
    LinearLayout container;
    Unbinder unbinder;
    private List<String> oneList = new ArrayList<>();
    private List<String> twoList = new ArrayList<>();
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TreeNode root = TreeNode.root();
        ObserverOnNextListener<VideoListBean> listener = new ObserverOnNextListener<VideoListBean>() {
            @Override
            public void onNext(VideoListBean data) {
                //androidTreeListView实现树形二级列表
                int length = data.getData().size();
                for (int i = 0; i < length; i++) {
                    oneList.add(data.getData().get(i).getTitle());
                    TreeNode folder = new TreeNode(new SelectableHeaderHolder.IconTreeItem(oneList.get(i))).setViewHolder(new SelectableHeaderHolder(getActivity()));
                    fillFolder(folder, i, data);
                    root.addChildren(folder);
                }
                AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
                //tView.setDefaultAnimation(true);
                container.addView(tView.getView());
            }
        };
        ApiMethods.getVideoList(new ProgressObserver<VideoListBean>(getActivity(), listener), (RxAppCompatActivity) getActivity());
    }

    public static VideoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void fillFolder(TreeNode folder, int i, VideoListBean data) {
        TreeNode file;
        int length = data.getData().get(i).getChild().size();
        for (int j = 0; j < length; j++) {
            twoList.add(data.getData().get(i).getChild().get(j).getTitle());
            file = new TreeNode(twoList.get(j)).setViewHolder(new SelectableItemHolder(getActivity()));
            folder.addChildren(file);
            file.setClickListener(new TreeNode.TreeNodeClickListener() {
                @Override
                public void onClick(TreeNode node, Object value) {
                    Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
