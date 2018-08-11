package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingListPreviews;
import ru.gcsales.app.domain.interactor.RemoveShoppingList;
import ru.gcsales.app.domain.model.ShoppingListPreview;
import ru.gcsales.app.presentation.mvp.view.ShoppingListPreviewView;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
@InjectViewState
public class ShoppingListPreviewPresenter extends MvpPresenter<ShoppingListPreviewView> {

    @Inject
    GetShoppingListPreviews mGetShoppingListPreviews;
    @Inject
    AddShoppingList mAddShoppingList;
    @Inject
    RemoveShoppingList mRemoveShoppingList;

    public ShoppingListPreviewPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadData() {
        getViewState().showProgress();
        mGetShoppingListPreviews.execute(new GetShoppingListPreviewsObserver(), null);
    }

    public void addShoppingList(String name) {
        mAddShoppingList.execute(new AddShoppingListObserver(), AddShoppingList.Params.forShoppingList(name));
    }

    public void removeShoppingList(ShoppingListPreview preview) {
        getViewState().removeItem(preview);
        mRemoveShoppingList.execute(new RemoveShoppingListObserver(),
                RemoveShoppingList.Params.forShoppingList(preview.getId()));
    }

    private final class GetShoppingListPreviewsObserver extends DisposableObserver<List<ShoppingListPreview>> {

        @Override
        public void onNext(List<ShoppingListPreview> shoppingListPreviewList) {
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

    private final class AddShoppingListObserver extends DisposableObserver<ShoppingListPreview> {

        @Override
        public void onNext(ShoppingListPreview preview) {
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
