package com.botian.yihu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.bean.RegisterBean;
import com.botian.yihu.contranct.RegisterContranct;
import com.botian.yihu.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterContranct.RegisterView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.acet_register_user)
    AppCompatEditText acetRegisterUser;
    @BindView(R.id.btn_identify)
    Button btnIdentify;
    @BindView(R.id.register_btn)
    Button loginBtn;
    @BindView(R.id.acet_register_pwd)
    AppCompatEditText acetRegisterPwd;
    @BindView(R.id.acet_register_identify)
    AppCompatEditText acetRegisterIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.back, R.id.btn_identify, R.id.register_btn})
    public void onViewClicked(View view) {
        String str;//手机号
        String str2;//验证码
        String str3;//密码
        RegisterPresenter registerpresenter;//创建P层对象 传入本身 因为P层的构造函数是IView接口 而本类实现了IView接口 将本身传进去之后 在P层进行交互的V接口获取到数据 在本类实现的V层接口方法里的数据就可以用了
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_identify://获取验证码
                str = acetRegisterUser.getText().toString();
                registerpresenter = new RegisterPresenter(this);
                registerpresenter.presenter(this,str);
                break;
            case R.id.register_btn://注册
                str = acetRegisterUser.getText().toString();
                str2 = acetRegisterIdentify.getText().toString();
                str3 = acetRegisterPwd.getText().toString();
                registerpresenter = new RegisterPresenter(this);
                registerpresenter.presenter(this,str,str2,str3);
                break;
        }
    }

    @Override
    public void view(RegisterBean data) {
        String d=data.getMsg();
        Toast.makeText(this,d,Toast.LENGTH_SHORT).show();
    }
}