package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.presentation.mvp.mapper.ShopModelDataMapper;
import ru.gcsales.app.presentation.mvp.model.ShopModel;
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
    @Inject
    ShopModelDataMapper mShopModelDataMapper;

    public ShopsPresenter() {
        AppApplication.getApplicationComponent().inject(this);
    }

    /**
     * Downloads shops from repository.
     */
    public void loadShops() {
        getViewState().showProgress();

        mGetShops.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> mShopModelDataMapper.transform(data))
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ShopModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ShopModel> shopModels) {
                        getViewState().setShops(shopModels);
                        getViewState().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
