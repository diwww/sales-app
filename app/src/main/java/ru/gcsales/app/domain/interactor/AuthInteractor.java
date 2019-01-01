package ru.gcsales.app.domain.interactor;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Authentication interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class AuthInteractor {

    // TODO: auth logic

    public Single<Boolean> checkLogin() {
        // TODO:
        return Single.just(true);
    }

    public Completable logIn() {
        // TODO:
        return null;
    }

    public Completable logOut() {
        // TODO:
        return null;
    }
}
