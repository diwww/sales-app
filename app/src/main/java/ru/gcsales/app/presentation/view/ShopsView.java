package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.model.ShopViewModel;

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
     * @param shopViewModels list of shop view models
     */
    void setShops(List<ShopViewModel> shopViewModels);

    /**
     * Shows an error message.
     *
     * @param error error message
     */
    void showError(String error);
}
