package com.botian.yihu.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.androidTreeListView.KaoQianPracticeChild;
import com.botian.yihu.androidTreeListView.KaoQianPracticeParent;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.KaoQianYaTiList;
import com.botian.yihu.beans.SearchKaoQianBuy;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.KaoqianBuyEvent;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KaoQianListActivity extends RxAppCompatActivity {
    @BindView(R.id.container)
    LinearLayout container;
    List<KaoQianYaTiList.DataBean> list = new ArrayList<>();//一级列表数据
    List<KaoQianYaTiList.DataBean> list2 = new ArrayList<>();//二级列表数据
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    //private int position = 0;
    private ACache mCache;
    private UserInfo userInfo;
    private View mview;
    private AndroidTreeView tView;
    //private TreeNode node1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_practice_list);
        //setTheme(R.style.dialog);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        title.setText("考前押题");
        queryFromServer();
    }

    public void queryFromServer() {
        ObserverOnNextListener<KaoQianYaTiList> listener = new ObserverOnNextListener<KaoQianYaTiList>() {
            @Override
            public void onNext(KaoQianYaTiList data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    KaoQianYaTiList.DataBean chapterCataLog = new KaoQianYaTiList.DataBean();
                    chapterCataLog.setId(data.getData().get(i).getId());
                    chapterCataLog.setPid(data.getData().get(i).getPid());
                    chapterCataLog.setName(data.getData().get(i).getName());
                    chapterCataLog.setPrice(data.getData().get(i).getPrice());
                    //chapterCataLog.setShare(data.getData().get(i).getShare());
                    if (data.getData().get(i).getPid() == 0) {
                        //ObserverOnNextListener<SearchKaoQianBuy> listener21 = new ObserverOnNextListener<SearchKaoQianBuy>() {
                        //@Override
                        //public void onNext(SearchKaoQianBuy data1) {

                        //if (data1.getCode() == 200) {
                        //chapterCataLog.setBuy(1);
                        //}else{
                        //chapterCataLog.setBuy(0);
                        //}
                        list.add(chapterCataLog);

                        // }
                        // };
                        //ApiMethods.SearchKaoQianBuy(new MyObserver<SearchKaoQianBuy>(listener21), userInfo.getId() + "", data.getData().get(i).getId() + "", KaoQianListActivity.this);

                        //list.add(chapterCataLog);
                    } else {
                        list2.add(chapterCataLog);

                    }

                }

                setList();
            }
        };


        ApiMethods.getKaoQianList(new ProgressObserver<KaoQianYaTiList>(this, listener), this);
    }

    public void setList() {
        TreeNode root = TreeNode.root();
        for (int i = 0; i < list.size(); i++) {
            final TreeNode folder = new TreeNode(new KaoQianPracticeParent.IconTreeItem(list.get(i).getName(), list.get(i).getId(), list.get(i).getPrice(), userInfo.getId() + "", this)).setViewHolder(new KaoQianPracticeParent(this));

            fillFolder(folder, list.get(i).getId(), list.get(i).getPrice(),list.get(i).getName());
            root.addChildren(folder);
        }
        tView = new AndroidTreeView(this, root);
        //tView.setDefaultAnimation(true);
        container.addView(tView.getView());
    }

    private void fillFolder(TreeNode folder, final int id, final String price, final String name) {
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).getPid() == id) {
                TreeNode file = new TreeNode(new KaoQianPracticeChild.IconTreeItem(list2.get(i).getName(), list2.get(i).getId())).setViewHolder(new KaoQianPracticeChild(this));
                folder.addChildren(file);
                file.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(final TreeNode node, final Object value) {
                        ObserverOnNextListener<SearchKaoQianBuy> listener21 = new ObserverOnNextListener<SearchKaoQianBuy>() {
                            @Override
                            public void onNext(SearchKaoQianBuy data1) {

                                if (data1.getCode() == 200) {
                                    Intent intent = new Intent(KaoQianListActivity.this, KaoQianActivity.class);
                                    int id2 = ((KaoQianPracticeChild.IconTreeItem) value).getNoid();
                                    intent.putExtra("typeid",id2+"");
                                    startActivity(intent);
                                } else {
                                    // 创建构建器
                                    AlertDialog.Builder builder = new AlertDialog.Builder(KaoQianListActivity.this);
                                    // 设置参数
                                    builder.setTitle("购买")
                                            .setMessage("是否确定购买")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    Intent intent = new Intent(KaoQianListActivity.this, KaoqianBuyActivity.class);
                                                    intent.putExtra("price", price);
                                                    intent.putExtra("id", id + "");
                                                    intent.putExtra("name", name + "");
                                                    startActivity(intent);
                                                }
                                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            // TODO Auto-generated method stub
                                            //Toast.makeText(MainActivity.this, "一点也不老实", 0)
                                            // .show();
                                        }
                                    });
                                    builder.create().show();

                                }

                            }
                        };
                        ApiMethods.SearchKaoQianBuy(new MyObserver<SearchKaoQianBuy>(listener21), userInfo.getId() + "", id + "", KaoQianListActivity.this);
                    }
                });
            }


        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(KaoqianBuyEvent messageEvent) {
        container.removeAllViews();
        setList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
