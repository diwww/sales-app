package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.domain.interactor.GetItems;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.AddItem;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.model.mapper.ShoppingListViewModelMapper;
import ru.gcsales.app.presentation.view.ItemsView;
import ru.gcsales.app.presentation.model.mapper.ItemViewModelMapper;

/**
 * ProductItem list presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
@InjectViewState
public class ItemsPresenter extends MvpPresenter<ItemsView> {

    public static final int VISIBLE_THRESHOLD = 5;

    @Inject
    GetItems mGetItems;
    @Inject
    AddItem mAddItem;
    @Inject
    GetShoppingLists mGetShoppingLists;

    private long mShopId;
    private String mCategory;
    private int mPage = 1;
    // If loading is in progress
    private boolean mLoading = false;
    // If the last page is loaded
    private boolean mEnd = false;

    private ItemViewModelMapper mItemViewModelMapper = new ItemViewModelMapper();
    private ShoppingListViewModelMapper mShoppingListViewModelMapper = new ShoppingListViewModelMapper();

    public ItemsPresenter(long shopId, String category) {
        AppApplication.getApplicationComponent().inject(this);
        mShopId = shopId;
        mCategory = category;
    }

    @Override
    public void onDestroy() {
        mGetItems.dispose();
        mAddItem.dispose();
        mGetShoppingLists.dispose();
    }


    /**
     * Downloads shopping lists.
     */
    public void loadShoppingLists() {
        mGetShoppingLists.execute(new GetShoppingListsObserver(), null);
    }

    /**
     * Downloads the next page of items.
     */
    public void loadNextPageProducts() {
        showProgress();
        mLoading = true;
        mGetItems.execute(new GetItemsObserver(), GetItems.Params.get(mShopId, mCategory, mPage));
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

    /**
     * Adds the item to the shopping list.
     *
     * @param itemViewModel         the item to add
     * @param shoppingListViewModel the shopping list to add the item to
     */
    public void addItem(ItemViewModel itemViewModel, ShoppingListViewModel shoppingListViewModel) {

        mAddItem.execute(new AddItemObserver(shoppingListViewModel),
                AddItem.Params.get(shoppingListViewModel.getId(), itemViewModel.getId()));
    }

    private void showProgress() {
        if (mPage == 1) {
            getViewState().showInitialProgress();
        } else {
            getViewState().showPageProgress();
        }
    }

    private void hideProgress() {
        // If initial items are loaded
        if (mPage == 1) {
            getViewState().hideInitialProgress();
        } else {
            getViewState().hidePageProgress();
        }
    }


    private final class GetItemsObserver extends DisposableObserver<List<Item>> {

        @Override
        public void onNext(List<Item> items) {
            // End signal to prevent further loading
            if (items.isEmpty()) {
                mEnd = true;
            } else {
                getViewState().addItems(mItemViewModelMapper.transform(items));
            }
        }

        @Override
        public void onError(Throwable e) {
            hideProgress();
            mLoading = false;
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            hideProgress();
            mLoading = false;
            mPage++;
        }
    }

    private final class AddItemObserver extends DisposableObserver<String> {

        private ShoppingListViewModel mShoppingListViewModel;

        public AddItemObserver(ShoppingListViewModel shoppingListViewModel) {
            mShoppingListViewModel = shoppingListViewModel;
        }

        @Override
        public void onNext(String s) {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
            getViewState().showItemAdded(mShoppingListViewModel.getId(), mShoppingListViewModel.getName());
        }
    }

    private final class GetShoppingListsObserver extends DisposableObserver<List<ShoppingList>> {

        @Override
        public void onNext(List<ShoppingList> shoppingLists) {
            getViewState().setShoppingLists(mShoppingListViewModelMapper.transform(shoppingLists));
        }

        @Override
        public void onError(Throwable e) {
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }
}
