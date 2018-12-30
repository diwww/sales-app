package ru.gcsales.app.presentation.model;

/**
 * Base item to store in {@link ru.gcsales.app.presentation.view.adapter.ItemsAdapter}
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
