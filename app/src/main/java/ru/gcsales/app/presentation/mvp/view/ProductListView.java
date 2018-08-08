package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.model.ShopInfo;

/**
 * Product list view.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public interface ProductListView extends MvpView {


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
     * Adds products to a list.
     *
     * @param products products to add
     */
    void addProducts(List<Product> products);

    /**
     * Sets the product list (replaces the old data).
     *
     * @param products products to set
     */
    void setProducts(List<Product> products);

    /**
     * Clears the product list.
     */
    void clearProducts();

    void setShopInfo(ShopInfo shopInfo);

    void setCategoryName(String category);

    void setNumItems(long numItems);
}
