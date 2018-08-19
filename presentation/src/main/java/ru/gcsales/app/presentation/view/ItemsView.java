package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

/**
 * ProductItem list view.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public interface ItemsView extends MvpView {

    /**
     * Shows initial progress bar in
     * the center of the screen.
     */
    void showInitialProgress();

    /**
     * Hides initial progress bar in
     * the center of the screen.
     */
    void hideInitialProgress();

    /**
     * Shows a progress bar
     * in the bottom of recycler view.
     */
    void showPageProgress();

    /**
     * Hides a progress bar
     * in the bottom of recycler view.
     */
    void hidePageProgress();

    /**
     * Shows an error message.
     *
     * @param error error message
     */
    void showError(String error);

    /**
     * Shows a message that item was added to the shopping list.
     *
     * @param shoppingListId   the shopping list id
     * @param shoppingListName the shopping list name
     */
    void showItemAdded(long shoppingListId, String shoppingListName);

    /**
     * Adds items to a list.
     *
     * @param items items to add
     */
    void addItems(List<ItemViewModel> items);

    /**
     * Sets the product list (replaces the old data).
     *
     * @param items items to set
     */
    void setItems(List<ItemViewModel> items);

    /**
     * Clears the product list.
     */
    void clearProducts();

    /**
     * Sets the shopping lists.
     *
     * @param shoppingLists list of shopping lists to set
     */
    void setShoppingLists(List<ShoppingListViewModel> shoppingLists);
}
