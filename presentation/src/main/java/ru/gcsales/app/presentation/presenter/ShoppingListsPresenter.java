package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.interactor.DeleteShoppingList;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.model.mapper.ShoppingListViewModelMapper;
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

    ShoppingListViewModelMapper mShoppingListViewModelMapper = new ShoppingListViewModelMapper();

    public ShoppingListsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        mGetShoppingLists.dispose();
        mAddShoppingList.dispose();
        mDeleteShoppingList.dispose();
    }

    public void loadData() {
        getViewState().showProgress();
        mGetShoppingLists.execute(new GetShoppingListsObserver(), null);
    }

    public void addShoppingList(String name) {
        mAddShoppingList.execute(new AddShoppingListObserver(), AddShoppingList.Params.get(name));
    }

    public void removeShoppingList(ShoppingListViewModel shoppingListViewModel) {
        getViewState().removeItem(shoppingListViewModel);
        mDeleteShoppingList.execute(new RemoveShoppingListObserver(),
                DeleteShoppingList.Params.get(shoppingListViewModel.getId()));
    }

    private final class GetShoppingListsObserver extends DisposableObserver<List<ShoppingList>> {

        @Override
        public void onNext(List<ShoppingList> shoppingLists) {
            getViewState().setData(mShoppingListViewModelMapper.transform(shoppingLists));
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
        public void onNext(ShoppingList shoppingList) {
            getViewState().addItem(mShoppingListViewModelMapper.transform(shoppingList));
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
