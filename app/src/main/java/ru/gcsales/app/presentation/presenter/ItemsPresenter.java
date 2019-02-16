package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.repository.ItemsRepositoryImpl;
import ru.gcsales.app.data.repository.ShoppingListRepositoryImpl;
import ru.gcsales.app.domain.interactor.ItemsInteractor;
import ru.gcsales.app.domain.interactor.ShoppingListInteractor;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.ui.items.ItemsView;

/**
 * Items presenter
 *
 * @author Maxim Surovtsev
 * @since 06/01/2019
 */
@InjectViewState
public class ItemsPresenter extends MvpPresenter<ItemsView> {

    private ItemsInteractor mItemsInteractor = new ItemsInteractor(new ItemsRepositoryImpl());
    private ShoppingListInteractor mShoppingListInteractor = new ShoppingListInteractor(new ShoppingListRepositoryImpl());
    private long mShopId;

    public ItemsPresenter(long shopId) {
        this.mShopId = shopId;
    }

    @Override
    protected void onFirstViewAttach() {
        loadItems(mShopId);
    }

    public void addToShoppingList(Item item) {
        // TODO: event bus call here
        // TODO: show toast on success addition to shopping list
        mShoppingListInteractor.addEntry(item)
                .subscribe(entry -> AppApplication.getSubject().onNext(entry), getViewState()::showError);
    }


    private Disposable loadItems(long shopId) {
        return mItemsInteractor.loadItems(mShopId)
                .doOnSubscribe(notUsed -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(getViewState()::showItems, getViewState()::showError);
    }
}
