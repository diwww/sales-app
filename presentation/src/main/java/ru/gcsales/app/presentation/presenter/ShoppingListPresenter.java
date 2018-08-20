package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.DeleteItem;
import ru.gcsales.app.domain.interactor.GetShoppingList;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.model.mapper.ShoppingListViewModelMapper;
import ru.gcsales.app.presentation.view.ShoppingListView;
import ru.gcsales.app.presentation.model.ItemViewModel;

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
    private ShoppingListViewModelMapper mMapper = new ShoppingListViewModelMapper();

    public ShoppingListPresenter(long shoppingListId) {
        mShoppingListId = shoppingListId;
        AppApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        mGetShoppingList.dispose();
        mDeleteItem.dispose();
    }

    public void loadData() {
        mGetShoppingList.execute(new GetShoppingListObserver(), GetShoppingList.Params.forShoppingList(mShoppingListId));
    }

    public void deleteItem(ItemViewModel itemViewModel) {
        mDeleteItem.execute(new DeleteItemObserver(itemViewModel), DeleteItem.Params.get(mShoppingListId, itemViewModel.getId()));

    }

    private final class GetShoppingListObserver extends DisposableObserver<ShoppingList> {

        @Override
        public void onNext(ShoppingList shoppingList) {
            getViewState().setData(mMapper.transform(shoppingList));
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private final class DeleteItemObserver extends DisposableObserver<String> {

        private ItemViewModel mItemViewModel;

        public DeleteItemObserver(ItemViewModel itemViewModel) {
            mItemViewModel = itemViewModel;
        }

        @Override
        public void onNext(String s) {
            getViewState().deleteItem(mItemViewModel);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }

    }
}
