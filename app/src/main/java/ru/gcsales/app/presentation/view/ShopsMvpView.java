package ru.gcsales.app.presentation.view;

import java.util.List;

import ru.gcsales.app.presentation.model.Shop;

public interface ShopsMvpView extends MvpView {

    void showData(List<Shop> data);
}
