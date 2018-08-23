package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/14/18
 */
public interface ShoppingListView extends MvpView {

    void showProgress();

    void hideProgress();

    void setData(ShoppingListViewModel shoppingList);

    void deleteItem(ItemViewModel item);
}
