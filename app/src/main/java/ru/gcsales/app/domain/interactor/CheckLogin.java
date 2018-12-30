package ru.gcsales.app.domain.interactor;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
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
    public void execute(DisposableObserver<Boolean> observer, Void params) {
        // Override to perform synchronous login check
        Observable<Boolean> observable = buildObservable(params);
        addDisposable(observable.subscribeWith(observer));
    }

    @Override
    Observable<Boolean> buildObservable(Void params) {
        return mAuthRepository.checkLogin();
    }
}
