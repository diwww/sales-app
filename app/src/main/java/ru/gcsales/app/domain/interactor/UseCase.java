package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;

public abstract class UseCase<T> {

    public abstract Observable<T> execute();
}
