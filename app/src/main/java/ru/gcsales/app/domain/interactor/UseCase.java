package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Base class for all use cases.
 *
 * @param <T> data type that must be returned
 * @param <P> optional parameters type
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public abstract class UseCase<T, P> {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Builds an {@link Observable} to perform a use case
     *
     * @param params optional params that could be needed for a use case
     * @return {@link Observable} object
     */
    abstract Observable<T> buildObservable(P params);

    /**
     * Starts an execution of the use case.
     *
     * @param observer observer to listen data on
     * @param params   optional params
     */
    public void execute(DisposableObserver<T> observer, P params) {
        Observable<T> observable = buildObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
