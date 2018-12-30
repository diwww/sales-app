package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

/**
 * @author Maxim Surovtsev
 * Created on 8/27/18
 */
public interface RegisterView extends MvpView {
    void showProgress();

    void hideProgress();

    void showError(String error);

    void onSuccessRegister();
}
