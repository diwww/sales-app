package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.presentation.mvp.view.LoginView;

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
        mLogin.execute(new LoginObserver(), Login.Params.forUser(username, password));
    }

    private final class LoginObserver extends DisposableObserver<Response<String>> {

        @Override
        public void onNext(Response<String> response) {
            if (response.isSuccessful()) {
                getViewState().showToken(response.body());
                getViewState().onSuccessLogin();
            } else {
                try {
                    getViewState().showError(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }
}
