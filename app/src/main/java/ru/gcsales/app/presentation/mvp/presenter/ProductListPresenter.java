package ru.gcsales.app.presentation.mvp.presenter;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.presentation.mvp.mapper.ProductModelDataMapper;
import ru.gcsales.app.presentation.mvp.view.ProductListView;

/**
 * Product list presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {

    private static final String TAG = "ProductListPresenter";

    public static final int VISIBLE_TRESHOLD = 2;
    @Inject
    GetProducts mGetProducts;
    @Inject
    ProductModelDataMapper mProductModelDataMapper;

    private long mShopId;
    private int mPage = 1;
    // If loading is in progress
    private boolean mLoading = false;
    // If the last page is loaded
    private boolean mEnd = false;

    public ProductListPresenter(long shopId) {
        AppApplication.getApplicationComponent().inject(this);
        mShopId = shopId;
    }

    /**
     * Downloads the next page of products.
     */
    public void loadNextPageProducts() {
        getViewState().showProgress();
        mLoading = true;
        mGetProducts.execute(new ProductsObserver(), GetProducts.Params.forShop(mShopId, mPage));
    }

    /**
     * Handles RecyclerView's scroll.
     *
     * @param totalItems           total number of items in RecyclerView
     * @param lastVisibleItemIndex last visible item index in RecyclerView
     */
    public void onScrolled(int totalItems, int lastVisibleItemIndex) {
        Log.d(TAG, "onScrolled");
        if (!mEnd && !mLoading && totalItems <= (lastVisibleItemIndex + VISIBLE_TRESHOLD)) {
            loadNextPageProducts();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        mGetProducts.dispose();
    }

    private final class ProductsObserver extends DisposableObserver<List<Product>> {
        @Override
        public void onNext(List<Product> products) {
            if (products.isEmpty()) {
                mEnd = true;
            }
            getViewState().addProducts(mProductModelDataMapper.transform(products));
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError("Network error.");
            mLoading = false;
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
            mLoading = false;
            mPage++;
        }
    }
}
