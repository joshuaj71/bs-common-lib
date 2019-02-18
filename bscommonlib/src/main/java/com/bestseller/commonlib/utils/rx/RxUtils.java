package com.bestseller.commonlib.utils.rx;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by joshua on 2019/2/1.
 */
public class RxUtils {
    public static <T> MaybeTransformer<T, T> defaultSchedulers_maybe() {
        return new MaybeTransformer<T, T>() {
            @Override
            public MaybeSource<T> apply(Maybe<T> upstream) {
                return upstream
                        .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                                .io());
            }
        };
    }


    public static <T> ObservableTransformer<T, T> defaultSchedulers_observable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> defaultSchedulers_flow() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io());

            }
        };
    }

    public static <T> SingleTransformer<T, T> defaultSchedulers_single() {
        return new SingleTransformer<T, T>() {

            @Override
            public SingleSource<T> apply(@NonNull Single<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io());
            }
        };
    }

    public static <T> MaybeTransformer<T, T> all_io_maybe() {
        return new MaybeTransformer<T, T>() {
            @Override
            public MaybeSource<T> apply(Maybe<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> all_io_flow() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> SingleTransformer<T, T> all_io_single() {
        return new SingleTransformer<T, T>() {
            @Override
            public SingleSource<T> apply(@NonNull Single<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> all_io_observable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }





    class SchedulerTransform<T> implements ObservableTransformer<T, T>,
            FlowableTransformer<T, T>,
            SingleTransformer<T, T>,
            MaybeTransformer<T, T>,
            CompletableTransformer {

        @Override
        public CompletableSource apply(@NonNull Completable upstream) {
            return null;
        }

        @Override
        public Publisher<T> apply(@NonNull Flowable<T> upstream) {
            return null;
        }

        @Override
        public MaybeSource<T> apply(@NonNull Maybe<T> upstream) {
            return null;
        }

        @Override
        public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
            return null;
        }

        @Override
        public SingleSource<T> apply(@NonNull Single<T> upstream) {
            return null;
        }
    }
}
