package ru.gcsales.app.domain.interactor;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.Observable;
import retrofit2.Response;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class Login extends UseCase<Response<String>, Login.Params> {

    private AuthRepository mAuthRepository;

    public Login(AuthRepository authRepository) {
        mAuthRepository = authRepository;
    }

    @Override
    Observable<Response<String>> buildObservable(Params params) {
        try {
            String hashedPassword = sha256(params.password);
            return mAuthRepository.login(params.username, hashedPassword)
                    .doOnNext(res -> {
                        if (res.isSuccessful()) {
                            // TODO: нормально ли записывать токен в doOnNext?
                            mAuthRepository.setToken(res.body());
                        }
                    });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Observable.empty();
    }

    public static class Params {

        private String username;
        private String password;

        public Params(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public static Params forUser(String username, String password) {
            return new Params(username, password);
        }
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
