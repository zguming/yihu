package com.botian.yihu.model;

import android.content.Context;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.bean.LoginBean;
import com.botian.yihu.contranct.LoginContranct;

public class LoginModel implements LoginContranct.LoginModel {

    @Override
    public void model(final LoginContranct.LoginModel.CallBack callBack, Context context, String phone,  String pwd) {
        ObserverOnNextListener<LoginBean> listener = new ObserverOnNextListener<LoginBean>() {
            @Override
            public void onNext(LoginBean data) {
                //请求到数据后 将数据保存到callback接口的方法里
                //用于将数据回调给P层 在P层里将数据给V
                callBack.callData(data);

            }
        };
        ApiMethods.login(new ProgressObserver<LoginBean>(context, listener), phone,  pwd);
    }

}
