package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.DeleteItem;
import ru.gcsales.app.domain.interactor.GetShoppingList;
import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.mvp.view.ShoppingListView;

/**
 * @author Maxim Surovtsev
 * Created on 8/14/18
 */
@InjectViewState
public class ShoppingListPresenter extends MvpPresenter<ShoppingListView> {

    @Inject
    GetShoppingList mGetShoppingList;

    @Inject
    DeleteItem mDeleteItem;

    private long mShoppingListId;

    public ShoppingListPresenter(long shoppingListId) {
        mShoppingListId = shoppingListId;
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadData() {
        mGetShoppingList.execute(new ShoppingListObserver(), GetShoppingList.Params.forShoppingList(mShoppingListId));
    }

    public void deleteItem(ProductItem productItem) {
        // FIXME: mock observer
        mDeleteItem.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
                getViewState().deleteItem(productItem);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        }, DeleteItem.Params.forItem(mShoppingListId, productItem.getId()));

    }

    private final class ShoppingListObserver extends DisposableObserver<ShoppingList> {

        @Override
        public void onNext(ShoppingList shoppingList) {
            getViewState().setData(shoppingList);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
