package com.botian.yihu.presenter;

import android.content.Context;

import com.botian.yihu.data.LoginBean;
import com.botian.yihu.contranct.LoginContranct;
import com.botian.yihu.model.LoginModel;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class LoginPresenter implements LoginContranct.LoginPresenter{
    //创建M层对象
    private LoginModel loginModel;
    //创建V层接口的对象
    private LoginContranct.LoginView loginView;

    //构造方法的参数为V层的接口对象
    public LoginPresenter(LoginContranct.LoginView loginView) {
        //待会展示数据的类实现V接口 创建P层的时候 将本身传进来 也就是说P层和展示数据的类他俩使用的是共同的一个V层接口 自然这个V层接口方法里的数据就可以共用了
        this.loginView = loginView;
        //创建M层的时候自然运行M层实现的请求数据方法 现在可以理解为已经请求到了数据
        loginModel = new LoginModel();
    }
    @Override//在这个方法里进行M层和V层的交互
    public void presenter(Context context,String phone,String pwd,RxAppCompatActivity yy) {
        //M层创建保存数据的callback接口对象 这个接口里方法的参数就是数据集合
        loginModel.model(new LoginContranct.LoginModel.CallBack() {
            @Override
            public void callData(LoginBean data) {
                //然后再用V层接口对象保存数据 在V层里展示出来
                loginView.view(data);
            }
        },context,phone,pwd,yy);

    }

}
