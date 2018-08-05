package ru.gcsales.app.presentation.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.mapper.model.ProductModelDataMapper;
import ru.gcsales.app.mapper.model.ShopInfoModelDataMapper;
import ru.gcsales.app.presentation.mvp.model.ShopInfoModel;
import ru.gcsales.app.presentation.mvp.model.ShopModel;
import ru.gcsales.app.presentation.mvp.view.ProductListView;

/**
 * Product list presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {

    public static final int VISIBLE_THRESHOLD = 2;
    public static final int FIRST_PAGE = 1;

    @Inject
    GetProducts mGetProducts;
    @Inject
    ProductModelDataMapper mProductModelDataMapper;

    @Inject
    GetShopInfo mGetShopInfo;
    @Inject
    ShopInfoModelDataMapper mShopInfoModelDataMapper;

    private long mShopId;
    private String mCategory;
    private int mPage;
    // If loading is in progress
    private boolean mLoading = false;
    // If the last page is loaded
    private boolean mEnd = false;

    public ProductListPresenter(long shopId) {
        AppApplication.getApplicationComponent().inject(this);
        mShopId = shopId;
    }

    @Override
    public void onDestroy() {
        mGetProducts.dispose();
    }

    /**
     * Loads initial items (unfiltered).
     */
    public void loadUnfiltered() {
        mCategory = null;
        mPage = FIRST_PAGE;
        getViewState().clearProducts();
        loadNextPageProducts();
    }

    /**
     * Loads items filtered by category.
     *
     * @param category category to filter by
     */
    public void loadCategory(String category) {
        mCategory = category;
        mPage = FIRST_PAGE;
        getViewState().clearProducts();
        loadNextPageProducts();
    }

    /**
     * Downloads the next page of products.
     */
    private void loadNextPageProducts() {
        getViewState().showProgress();
        mLoading = true;
        mGetProducts.execute(new ProductsObserver(), GetProducts.Params.forShop(mShopId, mCategory, mPage));
    }

    /**
     * Loads shop info (num items, categories, shop name, shop logo).
     */
    public void loadShopInfo() {
        mGetShopInfo.execute(new ShopInfoObserver(), GetShopInfo.Params.forShop(mShopId));
    }

    /**
     * Handles RecyclerView's scroll.
     *
     * @param totalItems           total number of items in RecyclerView
     * @param lastVisibleItemIndex last visible item index in RecyclerView
     */
    public void onScrolled(int totalItems, int lastVisibleItemIndex) {
        if (!mEnd && !mLoading && totalItems <= (lastVisibleItemIndex + VISIBLE_THRESHOLD)) {
            loadNextPageProducts();
        }
    }

    private final class ProductsObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(List<Product> products) {
            // End signal to prevent further loading
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

    private final class ShopInfoObserver extends DisposableObserver<ShopInfo> {

        @Override
        public void onNext(ShopInfo shopInfo) {
            getViewState().setShopInfo(mShopInfoModelDataMapper.transform(shopInfo));
        }

        @Override
        public void onError(Throwable e) {
            // TODO
        }

        @Override
        public void onComplete() {
            // TODO
        }
    }
}
