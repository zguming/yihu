package com.botian.yihu.contranct;

import android.content.Context;

import com.botian.yihu.data.RegisterBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class RegisterContranct {
    //M层接口及方法： 获取数据
    public interface RegisterModel {
        //M层获取请求数据的方法 方法参数为下面的接口对象
        void model(CallBack callBack,Context context,String mobile,RxAppCompatActivity yy);
        void model(CallBack callBack,Context context,String username,String mobile,String pwd,String version,String code,RxAppCompatActivity yy);

        //M层获取到数据之后 存入这个接口的方法然后把数据回调给P层
        interface CallBack {
            //方法的参数保存m层获取到的数据 然后回调给P层
            void callData(RegisterBean data);
        }
    }

    //P层接口及方法：M和V层的交互等逻辑（其实P层写不写接口都可以 用接口显得统一规范）
    public interface RegisterPresenter {
        void presenter(Context context, String mobile,RxAppCompatActivity yy);
        void presenter(Context context, String username,String mobile,String pwd,String version,String code,RxAppCompatActivity yy);
    }

    //V层接口 ：接收数据 显示数据
    public interface RegisterView {
        //方法的参数用于接收在P层里通过M层获取到的数据
        void view(RegisterBean data);
    }
}
