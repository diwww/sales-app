package ru.gcsales.app.presentation.view;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {
    /**
     * This method should implement showing a ProgressBar,
     * while downloading is in progress.
     */
    void showProgress();

    /**
     * This method should implement hiding a ProgressBar,
     * when downloading is finished.
     */
    void hideProgress();

    // TODO
//    void  showError(String error);
}
