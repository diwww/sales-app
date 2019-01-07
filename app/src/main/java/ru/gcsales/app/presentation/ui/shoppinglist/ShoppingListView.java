package ru.gcsales.app.presentation.ui.shoppinglist;

import java.util.List;

import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.presentation.ui.base.BaseView;

/**
 * Shopping list view
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public interface ShoppingListView extends BaseView {

    /**
     * Shows shopping list entries data
     *
     * @param entries shopping list entries data to be shown
     */
    void showEntries(List<ShoppingListEntry> entries);
}
