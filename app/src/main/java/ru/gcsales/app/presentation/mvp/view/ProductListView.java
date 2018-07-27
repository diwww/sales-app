package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.presentation.mvp.model.ProductModel;

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

    // FIXME: temporary
    void setProducts(List<ProductModel> products);
}
