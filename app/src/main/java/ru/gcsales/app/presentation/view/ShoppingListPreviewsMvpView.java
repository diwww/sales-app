package ru.gcsales.app.presentation.view;

import java.util.List;

import ru.gcsales.app.presentation.model.ShoppingListPreview;

public interface ShoppingListPreviewsMvpView extends MvpView {

    void showData(List<ShoppingListPreview> data);
}
