package com.botian.yihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.androidTreeListView.ChapterPracticeChild;
import com.botian.yihu.androidTreeListView.ChapterPracticeParent;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterPracticeListActivity extends RxAppCompatActivity {
    @BindView(R.id.container)
    LinearLayout container;
    List<ChapterPracticeListBean.DataBean> list = new ArrayList<>();//一级列表数据
    List<ChapterPracticeListBean.DataBean> list2 = new ArrayList<>();//二级列表数据
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_list);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
                queryFromServer();

    }

    public void queryFromServer() {
        ObserverOnNextListener<ChapterPracticeListBean> listener = new ObserverOnNextListener<ChapterPracticeListBean>() {
            @Override
            public void onNext(ChapterPracticeListBean data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    ChapterPracticeListBean.DataBean chapterCataLog = new ChapterPracticeListBean.DataBean();
                    chapterCataLog.setId(data.getData().get(i).getId());
                    chapterCataLog.setPid(data.getData().get(i).getPid());
                    chapterCataLog.setTypename(data.getData().get(i).getTypename());
                    if (data.getData().get(i).getPid() == 0) {
                       list.add(chapterCataLog);
                    } else {
                        list2.add(chapterCataLog);
                    }

                }
                setList();
            }
        };
        String mid="mid,eq"+ SubjectUtil.getSubjectNo();
        String mid2="mids,eq"+ SubjectUtil.getSubjectNo2();
        ApiMethods.getChapterPracticeList(new ProgressObserver<ChapterPracticeListBean>(this, listener), mid,mid2, this);
    }

    public void setList() {
        TreeNode root = TreeNode.root();
        for (int i = 0; i < list.size(); i++) {
            TreeNode folder = new TreeNode(new ChapterPracticeParent.IconTreeItem(list.get(i).getTypename(), list.get(i).getId())).setViewHolder(new ChapterPracticeParent(this));
            fillFolder(folder, list.get(i).getId());
            root.addChildren(folder);
        }
        AndroidTreeView tView = new AndroidTreeView(this, root);
        //tView.setDefaultAnimation(true);
        container.addView(tView.getView());
    }
    private void fillFolder(TreeNode folder, int id) {
        for (int i = position; i < list2.size(); i++) {
            if (list2.get(i).getPid() == id) {
                TreeNode file = new TreeNode(new ChapterPracticeChild.IconTreeItem(list2.get(i).getTypename(), list2.get(i).getId())).setViewHolder(new ChapterPracticeChild(this));
                folder.addChildren(file);
                file.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        int id = ((ChapterPracticeChild.IconTreeItem) value).getNoid();
                        Intent intent = new Intent(ChapterPracticeListActivity.this, PracticeAnswerActivity.class);
                        intent.putExtra("typeid",id);
                        startActivity(intent);
                    }
                });
            } else {
                position = i;
                break;
            }

        }
    }

}
