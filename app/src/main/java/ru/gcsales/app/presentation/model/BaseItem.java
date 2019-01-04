package ru.gcsales.app.presentation.model;

import ru.gcsales.app.presentation.ui.items.ItemsAdapter;

/**
 * Base item to store in {@link ItemsAdapter}
 *
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public interface BaseItem {

    /**
     * Gets the type of the item.
     *
     * @return int number representing the type
     */
    int getType();
}
