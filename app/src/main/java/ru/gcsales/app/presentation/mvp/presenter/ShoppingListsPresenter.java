package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.interactor.RemoveShoppingList;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.mvp.view.ShoppingListsView;

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
    RemoveShoppingList mRemoveShoppingList;

    public ShoppingListsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadData() {
        getViewState().showProgress();
        mGetShoppingLists.execute(new GetShoppingListPreviewsObserver(), null);
    }

    public void addShoppingList(String name) {
        mAddShoppingList.execute(new AddShoppingListObserver(), AddShoppingList.Params.forShoppingList(name));
    }

    public void removeShoppingList(ShoppingList preview) {
        getViewState().removeItem(preview);
        mRemoveShoppingList.execute(new RemoveShoppingListObserver(),
                RemoveShoppingList.Params.forShoppingList(preview.getId()));
    }

    private final class GetShoppingListPreviewsObserver extends DisposableObserver<List<ShoppingList>> {

        @Override
        public void onNext(List<ShoppingList> shoppingListPreviewList) {
            getViewState().setData(shoppingListPreviewList);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }

    private final class AddShoppingListObserver extends DisposableObserver<ShoppingList> {

        @Override
        public void onNext(ShoppingList preview) {
            getViewState().addItem(preview);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }

    private final class RemoveShoppingListObserver extends DisposableObserver<String> {

        @Override
        public void onNext(String s) {

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
