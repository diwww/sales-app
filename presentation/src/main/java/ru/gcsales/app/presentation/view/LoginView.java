package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface LoginView extends MvpView {

    void showError(String error);

    void showProgress();

    void hideProgress();

    void showToken(String token);

    void onSuccessLogin();
}
