package ru.gcsales.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.domain.interactor.ShopsInteractor;
import ru.gcsales.app.presentation.ui.shops.ShopsView;

/**
 * Shops presenter
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
@InjectViewState
public class ShopsPresenter extends MvpPresenter<ShopsView> {

    private ShopsInteractor mShopsInteractor = new ShopsInteractor(new ShopRepositoryImpl());

    @Override
    protected void onFirstViewAttach() {
        loadShops();
    }

    private Disposable loadShops() {
        return mShopsInteractor.getShops()
                .doOnSubscribe(notUsed -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(getViewState()::showShops, getViewState()::showError);
    }
}
