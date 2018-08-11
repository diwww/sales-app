package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListPreviewView extends MvpView {

    void setData(List<ShoppingList> data);

    void addItem(ShoppingList item);

    void removeItem(ShoppingList item);

    void showProgress();

    void hideProgress();

    void showError(String error);
}
