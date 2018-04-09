package com.botian.yihu.model;

import android.content.Context;

import com.botian.yihu.ObserverOnNextListener;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.bean.RegisterBean;
import com.botian.yihu.contranct.RegisterContranct;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class RegisterModel implements RegisterContranct.RegisterModel{
    @Override
    public void model(final CallBack callBack, Context context,String phone) {
        ObserverOnNextListener<RegisterBean> listener = new ObserverOnNextListener<RegisterBean>() {
            @Override
            public void onNext(RegisterBean data) {
                //请求到数据后 将数据保存到callback接口的方法里
                //用于将数据回调给P层 在P层里将数据给V
                callBack.callData(data);

            }
        };
        ApiMethods.getIdentify(new ProgressObserver<RegisterBean>(context,listener), phone);
    }

    @Override
    public void model(final CallBack callBack, Context context, String phone, String mobilelz, String pwd) {
        ObserverOnNextListener<RegisterBean> listener = new ObserverOnNextListener<RegisterBean>() {
            @Override
            public void onNext(RegisterBean data) {
                //请求到数据后 将数据保存到callback接口的方法里
                //用于将数据回调给P层 在P层里将数据给V
                callBack.callData(data);

            }
        };
        ApiMethods.register(new ProgressObserver<RegisterBean>(context,listener),phone,mobilelz,pwd);
    }

}
