package ru.gcsales.app.data.repository;


import android.content.Context;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        try {
            String hashedPassword = sha256(password);
            return mAuthService.login(new AuthService.UserInfo(username, hashedPassword)).toObservable();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Observable.error(e);
        }
    }

    @Override
    public Observable<String> register(String username, String password) {
        try {
            String hashedPassword = sha256(password);
            return mAuthService.register(new AuthService.UserInfo(username, hashedPassword)).toObservable();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Observable.error(e);
        }
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


    private String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
