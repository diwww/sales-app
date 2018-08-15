package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShopInfo;

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
     * Adds mProductItems to a list.
     *
     * @param productItems mProductItems to add
     */
    void addProducts(List<ProductItem> productItems);

    /**
     * Sets the product list (replaces the old data).
     *
     * @param productItems mProductItems to set
     */
    void setProducts(List<ProductItem> productItems);

    /**
     * Clears the product list.
     */
    void clearProducts();

    void setShopInfo(ShopInfo shopInfo);

    void setCategoryName(String category);

    void setNumItems(long numItems);
}
