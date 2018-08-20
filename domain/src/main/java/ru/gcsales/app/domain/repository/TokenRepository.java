package ru.gcsales.app.domain.repository;

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
    void setToken(String token);

    /**
     * Gets token.
     *
     * @return token string
     */
    String getToken();

    /**
     * Removes token.
     */
    void removeToken();

    /**
     * Gets auth header (token concatenated with auth info)
     *
     * @return auth header
     */
    String getAuthHeader();
}
