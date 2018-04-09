package com.botian.yihu;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener listener;

    public MyObserver( ObserverOnNextListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        //添加业务处理
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //添加业务处理
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        //添加业务处理
    }
}