package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;

/**
 * Shops view.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public interface ShopsView extends MvpView {

    /**
     * Shows a progress bar.
     */
    void showProgress();

    /**
     * Hides a progress bar.
     */
    void hideProgress();

    /**
     * Displays downloaded shops.
     *
     * @param shops list of {@link Shop} models
     */
    void setShops(List<Shop> shops);

    /**
     * Shows an error message.
     * @param error error message
     */
    void showError(String error);
}
