package com.botian.yihu.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.R;
import com.botian.yihu.androidTreeListView.ChapterPracticeChild;
import com.botian.yihu.androidTreeListView.ChapterPracticeParent;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ChapterPracticeListBean;
import com.botian.yihu.beans.No;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.database.ShareData;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.SubjectUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ChapterPracticeListActivity extends RxAppCompatActivity {
    @BindView(R.id.container)
    LinearLayout container;
    List<ChapterPracticeListBean.DataBean> list = new ArrayList<>();//一级列表数据
    List<ChapterPracticeListBean.DataBean> list2 = new ArrayList<>();//二级列表数据
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    //private int position = 0;
    private int zhenti;
    private String title1;
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
        mCache = ACache.get(this);
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        Intent intent = getIntent();
        title1 = intent.getStringExtra("title");
        title.setText(title1);
        zhenti = intent.getIntExtra("zhenti", 2);
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
                    chapterCataLog.setShare(data.getData().get(i).getShare());
                    if (data.getData().get(i).getPid() == 0) {
                        list.add(chapterCataLog);
                    } else {
                        list2.add(chapterCataLog);

                    }

                }
                setList();
            }
        };
        String mid = "mid,eq" + SubjectUtil.getSubjectNo();
        String mid2 = "mids,eq" + SubjectUtil.getSubjectNo2();
        ApiMethods.getChapterPracticeList(new ProgressObserver<ChapterPracticeListBean>(this, listener), mid, mid2, "zhenti,eq," + zhenti, this);
    }

    public void setList() {
        TreeNode root = TreeNode.root();
        for (int i = 0; i < list.size(); i++) {
            final TreeNode folder = new TreeNode(new ChapterPracticeParent.IconTreeItem(list.get(i).getTypename(), list.get(i).getId(), list.get(i).getShare())).setViewHolder(new ChapterPracticeParent(this));
            int share = list.get(i).getShare();
            if (share != 0) {
                int id = list.get(i).getId();
                int columnid = SubjectUtil.getSubjectNo2();
                List<ShareData> shareData = DataSupport.where("chapterId=" + id + ";" + "columnid=" + columnid).find(ShareData.class);
                if (shareData.size() > 0) {
                share=0;
                }
            }

            fillFolder(folder, list.get(i).getId(), share);
            root.addChildren(folder);
        }
        tView = new AndroidTreeView(this, root);
        //tView.setDefaultAnimation(true);
        container.addView(tView.getView());
    }

    private void fillFolder(TreeNode folder, final int id, final int share) {
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).getPid() == id) {
                TreeNode file = new TreeNode(new ChapterPracticeChild.IconTreeItem(list2.get(i).getTypename(), list2.get(i).getId())).setViewHolder(new ChapterPracticeChild(this));
                folder.addChildren(file);
                file.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        if (share != 0) {
                            // 创建构建器
                            AlertDialog.Builder builder = new AlertDialog.Builder(ChapterPracticeListActivity.this);
                            // 设置参数
                            builder.setTitle("分享解锁")
                                    .setMessage("分享后,解锁本套试题")
                                    .setPositiveButton("分享", new DialogInterface.OnClickListener() {// 积极

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            // TODO Auto-generated method stub
                                            //Toast.makeText(MainActivity.this, "恭喜你答对了", 0)
                                            // .show();
                                            showShare(id);
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
                        } else {
                            int id = ((ChapterPracticeChild.IconTreeItem) value).getNoid();
                            Intent intent = new Intent(ChapterPracticeListActivity.this, PracticeAnswerActivity.class);
                            intent.putExtra("typeid", id);
                            startActivity(intent);
                        }
                    }
                });
            }

        }
    }

    private void showShare(final int id) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("博天课堂下载");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://btsc.botian120.com/share");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("博天邀您一起来做题");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://btsc.botian120.com/uploads/image/20180724/ab76df172e61522aa986ec8826b7cf02.png");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://btsc.botian120.com/share");
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(ChapterPracticeListActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                //将分享数据存入数据库
                ShareData shareData = new ShareData();
                shareData.setChapterId(id);
                shareData.setColumnid(SubjectUtil.getSubjectNo2());
                shareData.save();
                ObserverOnNextListener<No> listener = new ObserverOnNextListener<No>() {
                    @Override
                    public void onNext(No data) {

                    }
                };
                //刷新列表
                container.removeAllViews();
                //position=0;
                setList();

                //提交分享数据
                ApiMethods.sendShareData(new MyObserver<No>(listener), userInfo.getId() + "", id + "", SubjectUtil.getSubjectNo2() + "", ChapterPracticeListActivity.this);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(ChapterPracticeListActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(ChapterPracticeListActivity.this, "分享取消", Toast.LENGTH_SHORT).show();

            }
        });

        // 启动分享GUI
        oks.show(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
