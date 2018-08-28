package ru.gcsales.app.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import ru.gcsales.app.presentation.AppApplication;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.model.mapper.ShopViewModelMapper;
import ru.gcsales.app.presentation.view.ShopsView;

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

    private ShopViewModelMapper mMapper = new ShopViewModelMapper();

    public ShopsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        loadShops();
    }

    @Override
    public void onDestroy() {
        mGetShops.dispose();
    }

    /**
     * Downloads shops from repository.
     */
    public void loadShops() {
        getViewState().showProgress();
        mGetShops.execute(new ShopsObserver(), null);
    }

    private final class ShopsObserver extends DisposableObserver<List<Shop>> {

        @Override
        public void onNext(List<Shop> shops) {
            getViewState().setShops(mMapper.transform(shops, null));
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
}
