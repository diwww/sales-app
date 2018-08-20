package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.AuthRepository;

/**
 * Use case for logging out.
 *
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class Logout extends UseCase<String, Void> {

    private AuthRepository mAuthRepository;

    @Inject
    public Logout(AuthRepository authRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mAuthRepository = authRepository;
    }

    @Override
    Observable<String> buildObservable(Void params) {
        return mAuthRepository.logout();
    }
}
