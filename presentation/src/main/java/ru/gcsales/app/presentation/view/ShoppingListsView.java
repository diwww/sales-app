package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListsView extends MvpView {

    void setData(List<ShoppingListViewModel> data);

    void addItem(ShoppingListViewModel item);

    void removeItem(ShoppingListViewModel item);

    void showProgress();

    void hideProgress();

    void showError(String error);
}
