package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.presentation.mvp.model.ShopModel;

public interface ShopsView extends MvpView {

    void showProgress();

    void hideProgress();

    void setShops(List<ShopModel> shops);
}
