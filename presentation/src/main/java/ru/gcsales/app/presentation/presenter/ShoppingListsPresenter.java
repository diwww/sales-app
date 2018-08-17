package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.interactor.DeleteShoppingList;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.view.ShoppingListsView;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
@InjectViewState
public class ShoppingListsPresenter extends MvpPresenter<ShoppingListsView> {

    @Inject
    GetShoppingLists mGetShoppingLists;
    @Inject
    AddShoppingList mAddShoppingList;
    @Inject
    DeleteShoppingList mDeleteShoppingList;

    public ShoppingListsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadData() {
        getViewState().showProgress();
        mGetShoppingLists.execute(new GetShoppingListPreviewsObserver(), null);
    }

    public void addShoppingList(String name) {
        mAddShoppingList.execute(new AddShoppingListObserver(), AddShoppingList.Params.get(name));
    }

    public void removeShoppingList(ShoppingList preview) {
        getViewState().removeItem(preview);
        mDeleteShoppingList.execute(new RemoveShoppingListObserver(),
                DeleteShoppingList.Params.forShoppingList(preview.getId()));
    }

    private final class GetShoppingListPreviewsObserver extends DisposableSingleObserver<List<ShoppingList>> {

        @Override
        public void onSuccess(List<ShoppingList> shoppingLists) {

            getViewState().hideProgress();
            getViewState().setData(shoppingLists);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }
    }

    private final class AddShoppingListObserver extends DisposableSingleObserver<ShoppingList> {

        @Override
        public void onSuccess(ShoppingList shoppingList) {
            getViewState().hideProgress();
            getViewState().addItem(shoppingList);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }
    }

    private final class RemoveShoppingListObserver extends DisposableSingleObserver<String> {

        @Override
        public void onSuccess(String s) {

        }

        @Override
        public void onError(Throwable e) {
            getViewState().showError(e.getMessage());
        }
    }
}
