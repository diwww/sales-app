package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.Item;

/**
 * ProductItem list view.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public interface ProductsView extends MvpView {


    /**
     * Shows a progress bar.
     */
    void showProgress();

    /**
     * Hides a progress bar.
     */
    void hideProgress();

    /**
     * Shows an error message.
     *
     * @param error error message
     */
    void showError(String error);

    /**
     * Adds items to a list.
     *
     * @param items items to add
     */
    void addProducts(List<Item> items);

    /**
     * Sets the product list (replaces the old data).
     *
     * @param items items to set
     */
    void setProducts(List<Item> items);

    /**
     * Clears the product list.
     */
    void clearProducts();
}
