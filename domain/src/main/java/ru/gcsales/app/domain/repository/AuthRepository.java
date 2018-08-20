package ru.gcsales.app.domain.repository;


import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Repository for authenticating a user.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface AuthRepository extends TokenRepository {
    Observable<String> login(String username, String password);

    void register(String username, String password);

    Observable<String> logout();

    Observable<Boolean> checkLogin();
}
