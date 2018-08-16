package ru.gcsales.app.domain.repository;


import io.reactivex.Single;

/**
 * Repository for authenticating a user.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface AuthRepository extends TokenRepository {
    Single<String> login(String username, String password);

    void register(String username, String password);

    void logout();

    boolean checkLogin();
}
