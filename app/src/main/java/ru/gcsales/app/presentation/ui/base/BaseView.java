package ru.gcsales.app.presentation.ui.base;

import com.arellomobile.mvp.MvpView;

/**
 * @author Maxim Surovtsev
 * @since 02/01/2019
 */
public interface BaseView extends MvpView {

    /**
     * Shows progress bar and hides other views
     *
     * @param visible {@code true} to show or {@code false} to hide
     */
    void showProgress(boolean visible);

    /**
     * Shows stub layout and hides other views
     *
     * @param visible {@code true} to show or {@code false} to hide
     */
    void showStub(boolean visible);

    /**
     * Shows error message
     *
     * @param error {@link Throwable} object to show
     */
    void showError(Throwable error);
}
