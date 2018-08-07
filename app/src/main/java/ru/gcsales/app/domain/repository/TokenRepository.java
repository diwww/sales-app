package ru.gcsales.app.domain.repository;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface TokenRepository {
    void setToken(String token);

    String getToken();

    void removeToken();
}
