package ru.gcsales.app.data.repository;


import io.reactivex.Observable;
import retrofit2.Response;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class AuthRepositoryImpl implements AuthRepository {

    private AuthService mAuthService;

    public AuthRepositoryImpl(AuthService authService) {
        mAuthService = authService;
    }

    public Observable<Response<String>> login(String username, String password) {
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
