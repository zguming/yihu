package com.botian.yihu.view;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.contranct.RegisterContranct;
import com.botian.yihu.data.RegisterBean;
import com.botian.yihu.presenter.RegisterPresenter;
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

public class RegisterActivity extends RxAppCompatActivity implements RegisterContranct.RegisterView {
    @BindView(R.id.acet_name)
    AppCompatEditText acetName;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
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
    @BindView(R.id.til_register_user)
    TextInputLayout tilRegisterUser;
    @BindView(R.id.til_register_identify)
    TextInputLayout tilRegisterIdentify;
    @BindView(R.id.til_register_pwd)
    TextInputLayout tilRegisterPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
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
            showError(tilRegisterUser, "请输入正确的手机号");
            return false;
        }
        return true;
    }

    /**
     * 验证验证码
     *
     * @param identify
     * @return
     */
    private boolean validateIdentify(String identify) {
        pref = getSharedPreferences("identifyData", MODE_PRIVATE);
        String iden = pref.getString("identify", "");
        if (identify.equals("") || !identify.equals(iden)) {
            showError(tilRegisterIdentify, "请输入正确的验证码");
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
            showError(tilRegisterPwd, "请输入6-16位登录密码");
            return false;
        }

        return true;
    }
    /**
     * 验证用户名
     *
     * @param password
     * @return
     */
    private boolean validateName(String password) {
        if (password.length() < 1 || password.length() > 10) {
            showError(tilName, "请输入1-10个字符");
            return false;
        }

        return true;
    }

    //2、手机号正则表达式
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @OnClick({R.id.back, R.id.btn_identify, R.id.register_btn})
    public void onViewClicked(View view) {
        String mobile;//手机号
        String identify;//验证码
        String pwd;//密码
        String username;//用户名
        RegisterPresenter registerpresenter;//创建P层对象 传入本身 因为P层的构造函数是IView接口 而本类实现了IView接口 将本身传进去之后 在P层进行交互的V接口获取到数据 在本类实现的V层接口方法里的数据就可以用了
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_identify://获取验证码
                mobile = acetRegisterUser.getText().toString();
                tilRegisterUser.setErrorEnabled(false);
                if (validateAccount(mobile)) {
                    registerpresenter = new RegisterPresenter(this);
                    registerpresenter.presenter(this, mobile, this);
                }
                break;
            case R.id.register_btn://注册
                String versionCode="0";
                PackageManager manager = getPackageManager();//获取包管理器
                try {
                    //通过当前的包名获取包的信息
                    PackageInfo info = manager.getPackageInfo(getPackageName(),0);//获取包对象信息
                    versionCode=info.versionCode+"";
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                mobile = acetRegisterUser.getText().toString();
                identify = acetRegisterIdentify.getText().toString();
                pwd = acetRegisterPwd.getText().toString();
                username = acetName.getText().toString();
                tilRegisterUser.setErrorEnabled(false);
                tilRegisterIdentify.setErrorEnabled(false);
                tilRegisterPwd.setErrorEnabled(false);
                tilName.setErrorEnabled(false);
                //验证用户名,验证码和密码
                if (validateAccount(mobile) && validateIdentify(identify) &&validateName(username)&& validatePassword(pwd)) {
                    registerpresenter = new RegisterPresenter(this);
                    registerpresenter.presenter(this, username,mobile,pwd,versionCode,identify,this);
                }
                break;
        }
    }

    @Override
    public void view(RegisterBean data) {
        String msg = data.getMsg();
        String identify = data.getRecode() + "";
        if (!(identify.equals("0"))) {
            editor = getSharedPreferences("identifyData", MODE_PRIVATE).edit();
            editor.putString("identify", identify);
            editor.apply();
        }
        if (msg.equals("注册成功")) {
            Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}