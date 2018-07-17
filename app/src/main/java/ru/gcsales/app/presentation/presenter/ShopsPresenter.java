package ru.gcsales.app.presentation.presenter;

import ru.gcsales.app.presentation.view.ShopsMvpView;

public class ShopsPresenter extends BasePresenter<ShopsMvpView> {

    public void loadShops() {
        checkViewAttached();
        getMvpView().showProgress();

        // TODO
    }
}
