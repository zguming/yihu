package com.botian.yihu.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botian.yihu.R;
import com.botian.yihu.contranct.ForgetPasswordContranct;
import com.botian.yihu.beans.RegisterBean;
import com.botian.yihu.presenter.ForgetPasswordPresenter;
import com.botian.yihu.util.ACache;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends RxAppCompatActivity  implements ForgetPasswordContranct.ForgetPasswordView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.acet_register_user)
    AppCompatEditText acetRegisterUser;
    @BindView(R.id.til_register_user)
    TextInputLayout tilRegisterUser;
    @BindView(R.id.acet_register_identify)
    AppCompatEditText acetRegisterIdentify;
    @BindView(R.id.til_register_identify)
    TextInputLayout tilRegisterIdentify;
    @BindView(R.id.btn_identify)
    Button btnIdentify;
    @BindView(R.id.acet_register_pwd)
    AppCompatEditText acetRegisterPwd;
    @BindView(R.id.til_register_pwd)
    TextInputLayout tilRegisterPwd;
    @BindView(R.id.acet_register_pwd2)
    AppCompatEditText acetRegisterPwd2;
    @BindView(R.id.til_register_pwd2)
    TextInputLayout tilRegisterPwd2;
    @BindView(R.id.register_btn)
    Button registerBtn;
    private ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setTheme(R.style.dialog);
        ButterKnife.bind(this);
        //缓存
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
        String iden = mCache.getAsString("identifyData");
        if (identify.equals("") ||!identify.equals(iden)) {
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
     * 验证密码2
     *
     * @param password
     * @return
     */
    private boolean validatePassword2(String password,String password2) {
        if (!password.equals(password2)) {
            showError(tilRegisterPwd2, "两次输入的密码不一致");
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
        String str;//手机号
        String str2;//验证码
        String str3;//密码
        String str4;//密码2
        ForgetPasswordPresenter forgetPasswordPresenter;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_identify:
                str = acetRegisterUser.getText().toString();
                tilRegisterUser.setErrorEnabled(false);
                if (validateAccount(str)){
                    forgetPasswordPresenter = new ForgetPasswordPresenter(this);
                    forgetPasswordPresenter.presenter(this, str,this);
                }
                break;
            case R.id.register_btn:
                str = acetRegisterUser.getText().toString();
                str2 = acetRegisterIdentify.getText().toString();
                str3 = acetRegisterPwd.getText().toString();
                str4 = acetRegisterPwd2.getText().toString();
                tilRegisterUser.setErrorEnabled(false);
                tilRegisterIdentify.setErrorEnabled(false);
                tilRegisterPwd.setErrorEnabled(false);
                //验证用户名,验证码和密码
                if (validateAccount(str) && validateIdentify(str2) && validatePassword(str3)&& validatePassword2(str3,str4)){
                    forgetPasswordPresenter = new ForgetPasswordPresenter(this);
                    forgetPasswordPresenter.presenter(this, str, str2, str3, str4,this);
                }
                break;
        }
    }
    @Override
    public void view(RegisterBean data) {
        String msg = data.getMsg();
        String identify=data.getRecode()+"";
        if(!(identify.equals("0"))){
            //缓存验证码
            mCache.put("identifyData", identify, 3600);
        }
        if(data.getMsg().equals("密码修改成功")){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
