package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.domain.interactor.CheckLogin;
import ru.gcsales.app.domain.interactor.Logout;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.presentation.view.HomeView;

/**
 * Home presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    @Inject
    Logout mLogout;
    @Inject
    CheckLogin mCheckLogin;

    public HomePresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        checkLogin();
    }

    @Override
    public void onDestroy() {
        mLogout.dispose();
        mCheckLogin.dispose();
    }

    public void checkLogin() {
        mCheckLogin.execute(new CheckLoginObserver(), null);
    }

    public void logOut() {
        mLogout.execute(new LogoutObserver(), null);
    }

    private final class CheckLoginObserver extends DisposableObserver<Boolean> {

        @Override
        public void onNext(Boolean isLoggedIn) {
            if (!isLoggedIn) {
                getViewState().openLoginActivity();
            }
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    private final class LogoutObserver extends DisposableObserver<String> {

        @Override
        public void onNext(String s) {
            getViewState().openLoginActivity();
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}
