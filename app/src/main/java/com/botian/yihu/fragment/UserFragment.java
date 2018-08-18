package com.botian.yihu.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.MyView.UpdataDialog;
import com.botian.yihu.R;
import com.botian.yihu.activity.AboutUsActivity;
import com.botian.yihu.activity.FeedbackErrorActivity;
import com.botian.yihu.activity.MymoneyActivity;
import com.botian.yihu.activity.PersonInfoActivity;
import com.botian.yihu.activity.SettingActivity;
import com.botian.yihu.activity.WebBrowserActivity;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.ShareList;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.beans.Version;
import com.botian.yihu.database.ShareData;
import com.botian.yihu.eventbus.LoginEvent;
import com.botian.yihu.eventbus.QuitLoginEvent;
import com.botian.yihu.eventbus.UserInfoEvent;
import com.botian.yihu.rxjavautil.MyObserver;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.util.ACache;
import com.botian.yihu.util.GetVersionName;
import com.botian.yihu.view.LoginActivity;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
//我的
public class UserFragment extends RxFragment {
    @BindView(R.id.tv_account)
    TextView tvAccount;
    Unbinder unbinder;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.rl_personal)
    RelativeLayout rlPersonal;
    @BindView(R.id.rl_suggest)
    RelativeLayout rlSuggest;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;
    @BindView(R.id.rl_share)
    RelativeLayout rlShare;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.rl_mymoney)
    RelativeLayout rlMymoney;
    private ACache mCache;
    private UserInfo userInfo;
    private int judgement = 0;//判断登录状态，0为登录，1为未登录

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        mCache = ACache.get(getActivity());
        initView();
    }

    private void initView() {
        //从缓存读取用户信息
        userInfo = (UserInfo) mCache.getAsObject("userInfo");
        if (userInfo != null) {
            tvAccount.setText(userInfo.getUsername());
            if (userInfo.getAvatar() != null) {
                Glide.with(this)
                        .load("http://btsc.botian120.com" + userInfo.getAvatar())
                        .into(userIcon);
            }
            //quitLogin.setVisibility(View.VISIBLE);
        } else {
            judgement = 1;
        }
    }

    public static UserFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.rl_mymoney,R.id.user_icon, R.id.tv_account, R.id.rl_about_us, R.id.rl_personal, R.id.rl_suggest, R.id.rl_version, R.id.rl_share, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mymoney:
                if (userInfo != null) {
                    Intent intent22 = new Intent(getActivity(), MymoneyActivity.class);
                    startActivity(intent22);
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.user_icon:
                //tvAccount.setText("登录/注册");
                if (judgement == 0) {
                    Intent intent77 = new Intent(getActivity(), PersonInfoActivity.class);
                    startActivity(intent77);
                } else {
                    Intent intent88 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent88);
                }
                break;
            case R.id.tv_account:
                if (judgement == 0) {
                    Intent intent18 = new Intent(getActivity(), PersonInfoActivity.class);
                    startActivity(intent18);
                } else {
                    Intent intent19 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent19);
                }
                break;
            //退出登录
            /*case R.id.quit_login:
                //缓存用户信息
                UserInfo userInfo4 = null;
                mCache.put("userInfo", userInfo4, 1200 * ACache.TIME_DAY);
                tvAccount.setText("登录/注册");
                userIcon.setImageResource(R.drawable.home_head_default);
                judgement = 1;
                //quitLogin.setVisibility(View.GONE);
                break;*/
            case R.id.rl_about_us:
                Intent intent10 = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent10);
                break;
            case R.id.rl_setting:
                Intent intent104 = new Intent(getActivity(), SettingActivity.class);
                intent104.putExtra("login", judgement);
                startActivity(intent104);
                break;
            case R.id.rl_share:
                if (judgement == 1) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    showShare();
                }
                break;
            case R.id.rl_personal:
                if (judgement == 1) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent99 = new Intent(getActivity(), PersonInfoActivity.class);
                    startActivity(intent99);
                }
                break;
            case R.id.rl_suggest:
                if (judgement == 1) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent991 = new Intent(getActivity(), FeedbackErrorActivity.class);
                    String userId = userInfo.getId() + "";
                    intent991.putExtra("userId", userId);
                    startActivity(intent991);
                }
                break;
            case R.id.rl_version:
                ObserverOnNextListener<Version> listener33 = new ObserverOnNextListener<Version>() {
                    @Override
                    public void onNext(final Version data) {
                        if (data.getCode() == 200) {
                            Toast.makeText(getActivity(), data.getMsg(), Toast.LENGTH_SHORT).show();

                        } else {
                            UpdataDialog updataDialog = new UpdataDialog(getActivity(), R.layout.dialog_version,
                                    new int[]{R.id.dialog_sure});
                            updataDialog.show();
                            if (data.getData().get(0).getStatus() == 1) {
                                updataDialog.setCanceledOnTouchOutside(false);
                            }
                            //TextView tvmsg = (TextView) updataDialog.findViewById(R.id.updataversion_msg);
                            TextView tvmsg = (TextView) updataDialog.findViewById(R.id.updataversion_msg);
                            TextView tvcode = (TextView) updataDialog.findViewById(R.id.updataversioncode);
                            String newVersion = data.getData().get(0).getName();
                            String content = data.getData().get(0).getContent();
                            tvcode.setText(newVersion);
                            String ta1 = Html.fromHtml(content).toString();
                            tvmsg.setText(Html.fromHtml(ta1));
                            updataDialog.setOnCenterItemClickListener(new UpdataDialog.OnCenterItemClickListener() {
                                @Override
                                public void OnCenterItemClick(UpdataDialog dialog, View view) {
                                    switch (view.getId()) {
                                        case R.id.dialog_sure:
                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            String cc = "http://btsc.botian120.com" + data.getData().get(0).getUrl();
                                            Uri content_url = Uri.parse(cc);
                                            intent.setData(content_url);
                                            startActivity(intent);
                                            break;
                                    }
                                }
                            });
                        }

                    }

                };
                String versionNama = GetVersionName.getVerName(getActivity());
                ApiMethods.getVersion(new MyObserver<Version>(listener33), versionNama, (RxAppCompatActivity) getActivity());
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UserInfoEvent messageEvent) {
        if (!messageEvent.getMessage().equals("")) {
            tvAccount.setText(messageEvent.getMessage());
        } else {
            userIcon.setImageBitmap(messageEvent.getPhoto());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(QuitLoginEvent messageEvent) {
        //缓存用户信息
        UserInfo userInfo4 = null;
        mCache.put("userInfo", userInfo4, 365 * ACache.TIME_DAY);
        tvAccount.setText("登录/注册");
        userIcon.setImageResource(R.drawable.home_head_default);
        judgement = 1;
        //quitLogin.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(LoginEvent messageEvent) {
        initView();
        judgement = 0;

        List<ShareData> shareData = DataSupport.findAll(ShareData.class);
        if (shareData.size() == 0) {
            ObserverOnNextListener<ShareList> listener37 = new ObserverOnNextListener<ShareList>() {
                @Override
                public void onNext(ShareList data) {
                    for (int i = 0; i < data.getData().size(); i++) {
                        ShareData shareData3 = new ShareData();
                        shareData3.setColumnid(data.getData().get(i).getColumnid());
                        shareData3.setChapterId(data.getData().get(i).getChaprerid());
                        shareData3.save();
                    }
                }

            };
            ApiMethods.getShareData(new MyObserver<ShareList>(listener37), "userid,eq," + userInfo.getId(), (RxAppCompatActivity) getActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void showShare() {
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
                Intent intent301 = new Intent(getActivity(), WebBrowserActivity.class);
                String url = "http://btsc.botian120.com/admin/demo/sharemoney?userid=" + userInfo.getId();
                intent301.putExtra("url", url);
                startActivity(intent301);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(getActivity(), "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(getActivity(), "分享取消", Toast.LENGTH_SHORT).show();

            }
        });
        // 启动分享GUI
        oks.show(getActivity());
    }

}
