package com.botian.yihu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.beans.UserInfo;
import com.botian.yihu.contranct.LoginContranct;
import com.botian.yihu.beans.LoginBean;
import com.botian.yihu.presenter.LoginPresenter;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class LoginActivity extends RxAppCompatActivity implements LoginContranct.LoginView {

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
    @BindView(R.id.til_login_pwd)
    TextInputLayout tilLoginPwd;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.ll_login_table)
    LinearLayout llLoginTable;
    private ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        mCache= ACache.get(this);

    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        /**
         * 校验手机号
         * @param mobile
         * @return 校验通过返回true，否则返回false
         */
        if (!isChinaPhoneLegal(account)) {
            showError(tilLoginPwd, "请输入正确的手机号");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (password.length() < 6 || password.length() > 16) {
            showError(tilPassword, "请输入6-16位登录密码");
            return false;
        }

        return true;
    }
        /*1、手机号开头集合
        166，
        176，177，178
        180，181，182，183，184，185，186，187，188，189
        145，147
        130，131，132，133，134，135，136，137，138，139
        150，151，152，153，155，156，157，158，159
        198，199*/

    //2、手机号正则表达式
    public static boolean isChinaPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @OnClick({R.id.back, R.id.tv_register, R.id.login_btn,R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_password:
                Intent intent = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
                case R.id.back:
                finish();
                break;
            case R.id.tv_register:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_btn://登录
                String username = acetUsername.getText().toString();
                String password = acetPassword.getText().toString();
                tilLoginPwd.setErrorEnabled(false);
                tilPassword.setErrorEnabled(false);
                //验证用户名和密码
                if (validateAccount(username) && validatePassword(password)) {
                    LoginPresenter loginpresenter = new LoginPresenter(this);
                    loginpresenter.presenter(this, username, password,this);
                }
                break;
        }
    }

    @Override
    public void view(LoginBean data) {
        String d = data.getMsg();
        if(data.getCode()==200){
            //如果登录成功缓存用户信息
            UserInfo userInfo=new UserInfo();
            userInfo.setId(data.getData().getId());
            userInfo.setToken(data.getData().getToken());
            userInfo.setUsername(data.getData().getUsername());
            userInfo.setMoblie(data.getData().getMoblie());
            mCache.put("userInfo", userInfo,  120 * ACache.TIME_DAY);
            Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
        }
    }
}