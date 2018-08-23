package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.presentation.view.LoginView;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    Login mLogin;

    public LoginPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        mLogin.dispose();
    }

    public void login(String username, String password) {
        getViewState().showProgress();
        mLogin.execute(new LoginObserver(), Login.Params.get(username, password));
    }

    private final class LoginObserver extends DisposableObserver<String> {

        @Override
        public void onNext(String s) {
            getViewState().onSuccessLogin();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }
}
