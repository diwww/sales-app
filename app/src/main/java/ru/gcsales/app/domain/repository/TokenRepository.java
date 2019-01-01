package ru.gcsales.app.domain.repository;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Repository for storing and getting auth token.
 * This interface should be extended by interfaces working with
 * authenticated requests.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface TokenRepository {

    /**
     * Saves token.
     *
     * @param token token to save
     */
    Completable setToken(String token);

    /**
     * Gets token.
     *
     * @return token string
     */
    Single<String> getToken();

    /**
     * Removes token.
     */
    Completable removeToken();

    /**
     * Gets auth header (token concatenated with auth info)
     *
     * @return auth header
     */
    Single<String> getAuthHeader();
}
