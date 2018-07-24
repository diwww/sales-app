package ru.gcsales.app.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.app.presentation.mvp.model.ShopModel;
import ru.gcsales.app.presentation.mvp.view.ShopsView;

@InjectViewState
public class ShopsPresenter extends MvpPresenter<ShopsView> {

    public void loadShops() {
        getViewState().showProgress();

        ShopModel shopModel = new ShopModel(1);
        shopModel.setImageUrl("http://gcsales.ru/static/dixy.png");
        shopModel.setName("Дикси");

        List<ShopModel> shopModels = new ArrayList<>();
        shopModels.add(shopModel);
        shopModels.add(shopModel);
        shopModels.add(shopModel);
        shopModels.add(shopModel);
        shopModels.add(shopModel);

        getViewState().setShops(shopModels);
    }
}
