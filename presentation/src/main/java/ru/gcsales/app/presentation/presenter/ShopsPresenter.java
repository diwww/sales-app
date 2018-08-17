package ru.gcsales.app.presentation.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.mvp.view.ShopsView;

/**
 * Shops presenter.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
@InjectViewState
public class ShopsPresenter extends MvpPresenter<ShopsView> {

    @Inject
    GetShops mGetShops;

    public ShopsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    /**
     * Downloads shops from repository.
     */
    public void loadShops() {
        getViewState().showProgress();
        mGetShops.execute(new ShopsObserver(), null);
    }

    @Override
    public void onDestroy() {
        mGetShops.dispose();
    }

    private final class ShopsObserver extends DisposableObserver<List<Shop>> {

        @Override
        public void onNext(List<Shop> shops) {
            getViewState().setShops(shops);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hideProgress();
            getViewState().showError("Network error.");
        }

        @Override
        public void onComplete() {
            getViewState().hideProgress();
        }
    }
}
