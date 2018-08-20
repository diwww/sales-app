package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * Use case for checking if the user is logged in.
 *
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class CheckLogin extends UseCase<Boolean, Void> {

    private AuthRepository mAuthRepository;

    @Inject
    public CheckLogin(PostExecutionThread postExecutionThread, AuthRepository authRepository) {
        super(postExecutionThread);
        mAuthRepository = authRepository;
    }

    @Override
    Observable<Boolean> buildObservable(Void params) {
        return mAuthRepository.checkLogin();
    }
}
