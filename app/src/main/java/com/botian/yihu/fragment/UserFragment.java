package com.botian.yihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.activity.AboutUsActivity;
import com.botian.yihu.activity.FeedbackErrorActivity;
import com.botian.yihu.activity.PersonInfoActivity;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.eventbus.LoginEvent;
import com.botian.yihu.eventbus.UserInfoEvent;
import com.botian.yihu.util.ACache;
import com.botian.yihu.view.LoginActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
//我的
public class UserFragment extends Fragment {
    @BindView(R.id.tv_account)
    TextView tvAccount;
    Unbinder unbinder;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.quit_login)
    Button quitLogin;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.rl_personal)
    RelativeLayout rlPersonal;
    @BindView(R.id.rl_suggest)
    RelativeLayout rlSuggest;
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
            if (userInfo.getAvatar() != null || !userInfo.getAvatar().equals("")) {
                Glide.with(this)
                        .load("http://btsc.botian120.com" + userInfo.getAvatar())
                        .into(userIcon);
            }
            quitLogin.setVisibility(View.VISIBLE);
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


    @OnClick({R.id.user_icon, R.id.tv_account, R.id.quit_login, R.id.rl_about_us, R.id.rl_personal,R.id.rl_suggest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.quit_login:
                //缓存用户信息
                UserInfo userInfo4 = null;
                mCache.put("userInfo", userInfo4, 1200 * ACache.TIME_DAY);
                tvAccount.setText("登录/注册");
                userIcon.setImageResource(R.drawable.home_head_default);
                judgement = 1;
                quitLogin.setVisibility(View.GONE);
                break;
            case R.id.rl_about_us:
                Intent intent10 = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent10);
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
                    String userId=userInfo.getId()+"";
                    intent991.putExtra("userId",userId);
                    startActivity(intent991);
                }
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
    public void Event1(LoginEvent messageEvent) {
        initView();
        judgement = 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}