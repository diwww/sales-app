package ru.gcsales.app.data.repository;


import android.content.Context;

import io.reactivex.Single;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class AuthRepositoryImpl extends TokenRepositoryImpl implements AuthRepository {

    private AuthService mAuthService;

    public AuthRepositoryImpl(Context context, AuthService authService) {
        super(context);
        mAuthService = authService;
    }

    public Single<String> login(String username, String password) {
        return mAuthService.login(new AuthService.UserInfo(username, password));
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void logout() {

    }

    @Override
    public boolean checkLogin() {
        return false;
    }
}
