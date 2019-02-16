package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.repository.ShoppingListRepositoryImpl;
import ru.gcsales.app.domain.interactor.ShoppingListInteractor;
import ru.gcsales.app.presentation.ui.shoppinglist.ShoppingListView;

/**
 * Shopping list presenter
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
@InjectViewState
public class ShoppingListPresenter extends MvpPresenter<ShoppingListView> {

    private ShoppingListInteractor mShoppingListInteractor = new ShoppingListInteractor(new ShoppingListRepositoryImpl());

    @Override
    protected void onFirstViewAttach() {
        loadShoppingList();
        AppApplication.getSubject().subscribe(getViewState()::addEntry, getViewState()::showError);
    }

    private Disposable loadShoppingList() {
        return mShoppingListInteractor.loadEntries()
                .doOnSubscribe(notUsed -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(getViewState()::showEntries, getViewState()::showError);
    }
}
