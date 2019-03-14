package com.bestseller.commonlib.base.rx;

import com.bestseller.commonlib.utils.LogUtils.LogUtil;

import java.util.concurrent.CancellationException;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by _Nan7 on 2018/1/23.
 */

public abstract class BaseMaybeObserver<T> implements MaybeObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull T result) {
        if (result != null)
            _onSuccess(result);
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
                LogUtil.d("BaseMaybeObserver CancellationException ...");
            }
        } else {
            _onError(new Exception("BaseMaybeObserver error , message is null"));
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void _onSuccess(T t);

    public abstract void _onError(Throwable e);
}
