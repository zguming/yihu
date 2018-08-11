package com.botian.yihu.model;

import android.content.Context;
import com.botian.yihu.rxjavautil.ObserverOnNextListener;
import com.botian.yihu.rxjavautil.ProgressObserver;
import com.botian.yihu.api.ApiMethods;
import com.botian.yihu.beans.SubjectBean;
import com.botian.yihu.contranct.SubjectContranct;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class SubjectModel implements SubjectContranct.SubjectModel {

    @Override
    public void model(final CallBack callBack, Context context,RxAppCompatActivity yy) {
        ObserverOnNextListener<SubjectBean> listener = new ObserverOnNextListener<SubjectBean>() {
            @Override
            public void onNext(SubjectBean data) {
                //请求到数据后 将数据保存到callback接口的方法里
                //用于将数据回调给P层 在P层里将数据给V
                callBack.callData(data);

            }
        };
        ApiMethods.getSubject(new ProgressObserver<SubjectBean>(context, listener),yy);
    }

}
