package com.botian.yihu.api;
import com.botian.yihu.ProgressObserver;
import com.botian.yihu.bean.LoginBean;
import com.botian.yihu.bean.RegisterBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {

    /**
     * 封装线程管理和订阅的过程
     */
    private static void ApiSubscribe(Observable observable, ProgressObserver observer) {
        observable.subscribeOn(Schedulers.io())
                //.unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用于获取验证码
     * @param observer 由调用者传过来的观察者对象
     * @param phone    手机号码 <IdentifyBean>
     */
    public static void getIdentify(ProgressObserver<RegisterBean> observer, String phone) {
        ApiSubscribe(Api.getApiService().getIdentify(phone), observer);
    }
    /**
     * 用于注册
     * @param observer 由调用者传过来的观察者对象
     * @param mobilelz 验证码
     * @param pwd 密码
     * @param phone    手机号码 <RegisterBean>
     */
    public static void register(ProgressObserver<RegisterBean> observer, String phone,String mobilelz,String pwd) {
        ApiSubscribe(Api.getApiService().register(phone,mobilelz,pwd), observer);
    }
    /**
     * 用于登录
     * @param observer 由调用者传过来的观察者对象
     * @param pwd 密码
     * @param phone    手机号码 <RegisterBean>
     */
    public static void login(ProgressObserver<LoginBean> observer, String phone, String pwd) {
        ApiSubscribe(Api.getApiService().login(phone,pwd), observer);
    }
    /**
     * 用于修改密码
     * @param observer 由调用者传过来的观察者对象
     * @param phone    手机号码 <RegisterBean>
     */
    public static void forget(ProgressObserver<RegisterBean> observer, String phone,String mobilelz,String pwd,String pwd2) {
        ApiSubscribe(Api.getApiService().forget(phone,mobilelz,pwd,pwd2), observer);
    }
}