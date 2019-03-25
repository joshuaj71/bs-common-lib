package com.bestseller.commonlib.base.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:Joshua
 * Date:2019/3/25
 * Description:
 */
public abstract class RxBusObservable<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
