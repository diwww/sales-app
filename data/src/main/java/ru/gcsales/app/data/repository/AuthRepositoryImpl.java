package ru.gcsales.app.data.repository;


import android.content.Context;

import io.reactivex.Observable;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * Implementation of {@link AuthRepository},
 * which gets a token from service and saves it Shared prefs.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class AuthRepositoryImpl extends TokenRepositoryImpl implements AuthRepository {

    private AuthService mAuthService;

    public AuthRepositoryImpl(Context context, AuthService authService) {
        super(context);
        mAuthService = authService;
    }

    /**
     * Performs a login.
     *
     * @param username username username
     * @param password password password
     * @return {@link Observable} of JWT token string
     */
    public Observable<String> login(String username, String password) {
        return mAuthService.login(new AuthService.UserInfo(username, password)).toObservable();
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public Observable<String> logout() {
        return Observable.just("Success log out!")
                .doOnNext(s -> removeToken());
    }

    @Override
    public Observable<Boolean> checkLogin() {
        return Observable.just(getToken() != null);
    }
}
