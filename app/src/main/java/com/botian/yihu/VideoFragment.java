package com.botian.yihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.botian.yihu.androidTreeListView.SelectableHeaderHolder;
import com.botian.yihu.androidTreeListView.SelectableItemHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * Created by WangChang on 2016/5/15.
 */
public class VideoFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout containerView =  view.findViewById(R.id.container);
        TreeNode root = TreeNode.root();
        TreeNode folder1 = new TreeNode(new SelectableHeaderHolder.IconTreeItem( "2018护士资格 课前导入")).setViewHolder(new SelectableHeaderHolder(getActivity()));
        TreeNode folder2 = new TreeNode(new SelectableHeaderHolder.IconTreeItem( "终极押密试卷")).setViewHolder(new SelectableHeaderHolder(getActivity()));
        TreeNode folder3 = new TreeNode(new SelectableHeaderHolder.IconTreeItem("第一章 基础护理知识和技能")).setViewHolder(new SelectableHeaderHolder(getActivity()));
        fillFolder(folder1);
        fillFolder(folder2);
        fillFolder(folder3);
        root.addChildren(folder1, folder2,folder3);
        AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        containerView.addView(tView.getView());
    }

    public static VideoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void fillFolder(TreeNode folder) {
        TreeNode file1 = new TreeNode("2018年护士资格考试-课前导入").setViewHolder(new SelectableItemHolder(getActivity()));
        TreeNode file2 = new TreeNode("2018年护士资格考试-课前导入").setViewHolder(new SelectableItemHolder(getActivity()));
        TreeNode file3 = new TreeNode("2018年护士资格考试-课前导入").setViewHolder(new SelectableItemHolder(getActivity()));
        folder.addChildren(file1, file2, file3);
    }
}
