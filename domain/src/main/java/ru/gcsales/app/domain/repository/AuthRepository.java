package ru.gcsales.app.domain.repository;


import io.reactivex.Observable;

/**
 * Repository for authenticating a user.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface AuthRepository extends TokenRepository {
    /**
     * Performs login.
     *
     * @param username username
     * @param password password
     * @return JWT token
     */
    Observable<String> login(String username, String password);

    /**
     * Performs register
     *
     * @param username username
     * @param password password
     * @return response, e.g. "OK"
     */
    Observable<String> register(String username, String password);

    /**
     * Performs logout.
     *
     * @return just empty string.
     */
    Observable<String> logout();

    /**
     * Checks if user is logged in.
     *
     * @return {@link Observable} of {@link Boolean} flag which shows login state
     */
    Observable<Boolean> checkLogin();
}
