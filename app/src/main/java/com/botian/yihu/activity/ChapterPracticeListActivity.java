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
import com.botian.yihu.database.ChapterPracticeList;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import org.litepal.crud.DataSupport;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterPracticeListActivity extends RxAppCompatActivity {
    @BindView(R.id.container)
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_list);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        //查询列表数据，优先从数据库查询，如果没有查询到再去服务器上查询
        List<ChapterPracticeList> chapterPracticeListData;
        chapterPracticeListData = DataSupport.where("pid==?","0").find(ChapterPracticeList.class);
            if (chapterPracticeListData.size() > 0) {
                setList();
            } else {
                queryFromServer();
            }
    }

    public void queryFromServer() {
        ObserverOnNextListener<ChapterPracticeListBean> listener = new ObserverOnNextListener<ChapterPracticeListBean>() {
            @Override
            public void onNext(ChapterPracticeListBean data) {
                //请求到的数据保存到数据库
                for (int i = 0; i < data.getData().size(); i++) {
                    ChapterPracticeList chapterPracticeList = new ChapterPracticeList();
                    chapterPracticeList.setNoid(data.getData().get(i).getId());
                    chapterPracticeList.setPid(data.getData().get(i).getPid());
                    chapterPracticeList.setTypename(data.getData().get(i).getTypename());
                    chapterPracticeList.save();
                }
                data.getData().clear();
                setList();
            }
        };
        ApiMethods.getChapterPracticeList(new ProgressObserver<ChapterPracticeListBean>(this, listener), "1", this);
    }

    public void setList() {
        TreeNode root = TreeNode.root();
        List<ChapterPracticeList> chapterPracticeListData;
        chapterPracticeListData = DataSupport.where("pid==?","0").find(ChapterPracticeList.class);
        for (int i = 0; i < chapterPracticeListData.size(); i++) {
            TreeNode folder = new TreeNode(new ChapterPracticeParent.IconTreeItem(chapterPracticeListData.get(i).getTypename(),chapterPracticeListData.get(i).getNoid())).setViewHolder(new ChapterPracticeParent(this));
            fillFolder(folder, chapterPracticeListData.get(i).getNoid());
            root.addChildren(folder);
        }
        AndroidTreeView tView = new AndroidTreeView(this, root);
        //tView.setDefaultAnimation(true);
        container.addView(tView.getView());
    }

    private void fillFolder(TreeNode folder, int id) {
        final List<ChapterPracticeList> chapterPracticeListData;
        chapterPracticeListData = DataSupport.where("pid==?", id + "").find(ChapterPracticeList.class);
        for (int i = 0; i < chapterPracticeListData.size(); i++) {
            TreeNode file = new TreeNode(new ChapterPracticeChild.IconTreeItem(chapterPracticeListData.get(i).getTypename(),chapterPracticeListData.get(i).getNoid())).setViewHolder(new ChapterPracticeChild(this));
            folder.addChildren(file);
            file.setClickListener(new TreeNode.TreeNodeClickListener() {
                @Override
                public void onClick(TreeNode node, Object value) {
                    int id=((ChapterPracticeChild.IconTreeItem) value).getNoid();
                    Intent intent = new Intent(ChapterPracticeListActivity.this, PracticeAnswerActivity.class);
                    intent.putExtra("typeid",id);
                    startActivity(intent);
                }
            });
        }
    }

}
