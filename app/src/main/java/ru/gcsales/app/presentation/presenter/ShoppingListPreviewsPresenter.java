package ru.gcsales.app.presentation.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.gcsales.app.presentation.model.ShoppingListPreview;
import ru.gcsales.app.presentation.view.ShoppingListPreviewsMvpView;

public class ShoppingListPreviewsPresenter extends BasePresenter<ShoppingListPreviewsMvpView> {

    public void loadShoppingListPreviews() {
        checkViewAttached();
        getMvpView().showProgress();

        // TODO
    }
}
