package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.presentation.ui.categories.CategoriesView;

/**
 * Categories interactor
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
@InjectViewState
public class CategoriesPresenter extends MvpPresenter<CategoriesView> {

    private long mShopId;

    public CategoriesPresenter(Long shopId) {
        mShopId = shopId;
    }

    @Override
    protected void onFirstViewAttach() {
        loadCategories();
    }

    private Disposable loadCategories() {
        // TODO
        return null;
    }
}
