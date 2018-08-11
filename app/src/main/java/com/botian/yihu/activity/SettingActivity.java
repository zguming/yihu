package com.botian.yihu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.database.CollectData;
import com.botian.yihu.database.NoteData;
import com.botian.yihu.database.WrongData;
import com.botian.yihu.eventbus.QuitLoginEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends RxAppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.rl_wrong)
    RelativeLayout rlWrong;
    @BindView(R.id.rl_note)
    RelativeLayout rlNote;
    @BindView(R.id.quit_login)
    Button quitLogin;
    private int judgement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        judgement = intent.getIntExtra("login", 1);
        if (judgement == 0) {
            quitLogin.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.back, R.id.rl_collect, R.id.rl_wrong, R.id.rl_note, R.id.quit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.quit_login:
                EventBus.getDefault().post(new QuitLoginEvent());
                // 创建构建器
                AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
                // 设置参数
                builder6.setTitle("退出登录")
                        .setMessage("是否确定退出登录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(SettingActivity.this, "已经退出登录", Toast.LENGTH_SHORT).show();
                                quitLogin.setVisibility(View.GONE);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub

                    }
                });
                builder6.create().show();
                break;
            case R.id.rl_collect:
                // 创建构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 设置参数
                builder.setTitle("清空收藏")
                        .setMessage("是否确定清空收藏")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                DataSupport.deleteAll(CollectData.class);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub

                    }
                });
                builder.create().show();
                break;
            case R.id.rl_wrong:
                // 创建构建器
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                // 设置参数
                builder2.setTitle("清空错题")
                        .setMessage("是否确定清空错题")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                DataSupport.deleteAll(WrongData.class);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub

                    }
                });
                builder2.create().show();
                break;
            case R.id.rl_note:
                // 创建构建器
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                // 设置参数
                builder3.setTitle("清空笔记")
                        .setMessage("是否确定清空笔记")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                DataSupport.deleteAll(NoteData.class);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub

                    }
                });
                builder3.create().show();
                break;
        }
    }

}
