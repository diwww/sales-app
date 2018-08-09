package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetShoppingListPreviews;
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

    public ShoppingListPreviewPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    public void loadData() {
        mGetShoppingListPreviews.execute(new ShoppingListPreviewObserver(), null);
    }

    private final class ShoppingListPreviewObserver extends DisposableObserver<List<ShoppingListPreview>> {

        @Override
        public void onNext(List<ShoppingListPreview> shoppingListPreviewList) {
            getViewState().setData(shoppingListPreviewList);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
