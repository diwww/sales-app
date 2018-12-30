package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/27/18
 */
public class Register extends UseCase<String, Register.Params> {

    private AuthRepository mAuthRepository;

    @Inject
    public Register(AuthRepository authRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mAuthRepository = authRepository;
    }

    @Override
    Observable<String> buildObservable(Params params) {
        return mAuthRepository.register(params.username, params.password);
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
}
