package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/14/18
 */
public interface ShoppingListView extends MvpView {

    void setData(ShoppingList shoppingList);

    void deleteItem(ProductItem productItem);
}
