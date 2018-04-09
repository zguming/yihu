package com.botian.yihu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.bean.LoginBean;
import com.botian.yihu.contranct.LoginContranct;
import com.botian.yihu.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class LoginActivity extends AppCompatActivity implements LoginContranct.LoginView{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.acet_username)
    AppCompatEditText acetUsername;
    @BindView(R.id.acet_password)
    AppCompatEditText acetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.back, R.id.tv_register, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn://登录
                String str = acetUsername.getText().toString();
                String str2 = acetPassword.getText().toString();
                LoginPresenter loginpresenter = new LoginPresenter(this);
                loginpresenter.presenter(this,str,str2);
                break;
        }
    }

    @Override
    public void view(LoginBean data) {
        String d=data.getMsg();
        Toast.makeText(this,d,Toast.LENGTH_SHORT).show();
    }
}