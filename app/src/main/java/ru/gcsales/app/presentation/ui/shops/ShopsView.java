package ru.gcsales.app.presentation.ui.shops;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseView;

/**
 * Shops view
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public interface ShopsView extends BaseView {

    /**
     * Shows shops data
     *
     * @param shops shops data to be shown
     */
    void showShops(List<Shop> shops);

    /**
     * Shows {@link ru.gcsales.app.presentation.ui.categories.CategoriesActivity} screen
     *
     * @param shop {@link Shop} model
     */
    void showCategoriesScreen(Shop shop);
}
