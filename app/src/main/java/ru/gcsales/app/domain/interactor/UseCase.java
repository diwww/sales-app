package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;

/**
 * Base class for all use cases.
 *
 * @param <T> data type that must be returned
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public abstract class UseCase<T> {

    /**
     * Starts an execution of the use case.
     * @return {@link Observable} of retrieved data
     */
    public abstract Observable<T> execute();
}
