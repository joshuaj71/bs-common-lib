package com.bestseller.commonlib.base.rx;

import com.bestseller.commonlib.utils.logutils.LogUtil;

import java.util.concurrent.CancellationException;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Joshua
 */
//public abstract class BaseSingleObserver<T> implements SingleObserver<T> {
public abstract class BaseSingleDisposableObserver<T> implements SingleObserver<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onSuccess(@NonNull T result) {
        if (result != null)
            _onSuccess(result, disposable);
        else
            _onError(new Throwable("error = 服务器返回值为null"));
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            //处理RxLife取消订阅的问题,这实际上并不是一个真正的错误
            //问题链接:https://github.com/trello/RxLifecycle/tree/2.x#unsubscription
            if (!(e instanceof CancellationException)) {
                e.printStackTrace();
                if (e.getMessage() == null) {
                    _onError(new Throwable(e.toString()));
                } else {
                    _onError(new Throwable(e.getMessage()));
                }
            } else {
                LogUtil.d("BaseSingleObserver CancellationException ...");
            }
        } else {
            _onError(new Exception("BaseSingleObserver error , message is null"));
        }
    }


    public abstract void _onSuccess(T t, Disposable disposable);

    public abstract void _onError(Throwable e);
}
