package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.data.repository.ItemsRepositoryImpl;
import ru.gcsales.app.domain.interactor.ItemsInteractor;
import ru.gcsales.app.presentation.ui.items.ItemsView;

/**
 * Items presenter
 *
 * @author Maxim Surovtsev
 * @since 06/01/2019
 */
@InjectViewState
public class ItemsPresenter extends MvpPresenter<ItemsView> {

    private ItemsInteractor mItemsInteractor = new ItemsInteractor(new ItemsRepositoryImpl(), null);
    private long mShopId;

    public ItemsPresenter(long shopId) {
        this.mShopId = shopId;
    }

    @Override
    protected void onFirstViewAttach() {
        loadItems(mShopId);
    }

    private Disposable loadItems(long shopId) {
        return mItemsInteractor.loadItems(mShopId)
                .doOnSubscribe(notUsed -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(getViewState()::showItems, getViewState()::showError);
    }
}
