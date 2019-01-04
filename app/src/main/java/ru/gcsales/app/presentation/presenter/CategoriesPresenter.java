package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.domain.interactor.CategoriesInteractor;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.categories.CategoriesView;

/**
 * Categories interactor
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
@InjectViewState
public class CategoriesPresenter extends MvpPresenter<CategoriesView> {

    private Shop mShop;
    private CategoriesInteractor mCategoriesInteractor = new CategoriesInteractor(new ShopRepositoryImpl());

    public CategoriesPresenter(Shop shop) {
        mShop = shop;
    }

    @Override
    protected void onFirstViewAttach() {
        loadCategories();
    }

    private Disposable loadCategories() {
        return mCategoriesInteractor.getCategories(mShop)
                .doOnSubscribe(notUsed -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(getViewState()::showCategories, getViewState()::showError);
    }
}
