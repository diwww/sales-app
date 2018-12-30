package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.gcsales.app.domain.executor.PostExecutionThread;

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
    private final PostExecutionThread mPostExecutionThread;

    /**
     * Constructs a new instance.
     *
     * @param postExecutionThread thread for publishing results
     */
    public UseCase(PostExecutionThread postExecutionThread) {
        mPostExecutionThread = postExecutionThread;
    }

    /**
     * Builds a {@link Observable} to perform a use case
     *
     * @param params optional params that could be needed for a use case
     * @return {@link Single} object
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
                .observeOn(mPostExecutionThread.getScheduler(), true);
        addDisposable(observable.subscribeWith(observer));
    }

    public PostExecutionThread getPostExecutionThread() {
        return mPostExecutionThread;
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
