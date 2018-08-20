package ru.gcsales.app.domain.interactor;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * Use case for logging in and getting auth token.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class Login extends UseCase<String, Login.Params> {

    private AuthRepository mAuthRepository;

    @Inject
    public Login(AuthRepository authRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mAuthRepository = authRepository;
    }

    @Override
    Observable<String> buildObservable(Params params) {
        try {
            String hashedPassword = sha256(params.password);
            return mAuthRepository.login(params.username, hashedPassword)
                    .doOnNext(res -> mAuthRepository.setToken(res));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Observable.empty();
    }

    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {

        private String username;
        private String password;

        public Params(String username, String password) {
            this.username = username;
            this.password = password;
        }

        /**
         * Gets params instance.
         *
         * @param username username
         * @param password password
         * @return new params instance
         */
        public static Params get(String username, String password) {
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
