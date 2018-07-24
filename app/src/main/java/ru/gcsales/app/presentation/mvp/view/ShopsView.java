package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.presentation.mvp.model.ShopModel;

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
     * @param shops list of {@link ShopModel} models
     */
    void setShops(List<ShopModel> shops);
}
