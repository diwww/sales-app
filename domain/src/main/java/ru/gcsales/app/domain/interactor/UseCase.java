package ru.gcsales.app.domain.interactor;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
     * Builds a {@link Single} to perform a use case
     *
     * @param params optional params that could be needed for a use case
     * @return {@link Single} object
     */
    abstract Single<T> buildSingle(P params);

    /**
     * Starts an execution of the use case.
     *
     * @param observer observer to listen data on
     * @param params   optional params
     */
    public void execute(DisposableSingleObserver<T> observer, P params) {
        Single<T> single = buildSingle(params)
                .subscribeOn(Schedulers.io())
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(single.subscribeWith(observer));
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
