package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/14/18
 */
public interface ShoppingListView extends MvpView {

    void setData(ShoppingList shoppingList);

    void deleteItem(Item item);
}
