package ru.gcsales.app.presentation.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.AddItem;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.presentation.mvp.view.ProductsView;

/**
 * ProductItem list presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
@InjectViewState
public class ProductsPresenter extends MvpPresenter<ProductsView> {

    public static final int VISIBLE_THRESHOLD = 2;
    public static final int FIRST_PAGE = 1;

    @Inject
    GetProducts mGetProducts;

    @Inject
    GetShopInfo mGetShopInfo;

    @Inject
    AddItem mAddItem;

    private long mShopId;
    private String mCategory;
    private int mPage;
    // If loading is in progress
    private boolean mLoading = false;
    // If the last page is loaded
    private boolean mEnd = false;

    public ProductsPresenter(long shopId) {
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
        getViewState().setCategoryName(null);
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
        getViewState().setCategoryName(category);
        loadNextPageProducts();
    }

    /**
     * Downloads the next page of mProductItems.
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

    public void addItem(long shoppingListId, long itemId) {
        // FIXME: mock observer
        mAddItem.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }, AddItem.Params.forItem(shoppingListId, itemId));
    }

    private final class ProductsObserver extends DisposableObserver<List<ProductItem>> {

        @Override
        public void onNext(List<ProductItem> productItems) {
            // End signal to prevent further loading
            if (productItems.isEmpty()) {
                mEnd = true;
            }
            getViewState().addProducts(productItems);
//            getViewState().setNumItems(productsInfo.getCount());
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
            getViewState().setShopInfo(shopInfo);
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
