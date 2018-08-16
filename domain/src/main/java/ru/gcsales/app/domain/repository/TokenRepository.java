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
    void setToken(String token);

    String getToken();

    void removeToken();

    String getAuthHeader();
}
