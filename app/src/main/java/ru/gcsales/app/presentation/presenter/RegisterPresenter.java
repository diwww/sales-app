package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.domain.interactor.Register;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.presentation.view.RegisterView;

/**
 * Register presenter.
 *
 * @author Maxim Surovtsev
 * Created on 8/27/18
 */
@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterView> {

    @Inject
    Register mRegister;

    public RegisterPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void register(String username, String password) {
        getViewState().showProgress();
        mRegister.execute(new RegisterObserver(), Register.Params.get(username, password));
    }

    private class RegisterObserver extends DisposableObserver<String> {

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
            getViewState().onSuccessRegister();
        }
    }
}
