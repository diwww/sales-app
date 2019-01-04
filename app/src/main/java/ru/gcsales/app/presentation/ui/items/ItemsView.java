package ru.gcsales.app.presentation.ui.items;

import java.util.List;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.ui.base.BaseView;

/**
 * Items view
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public interface ItemsView extends BaseView {

    /**
     * Shows initial items data
     *
     * @param items first page of items data
     */
    void showItems(List<Item> items);

    /**
     * Shows next page of items data
     *
     * @param items next page of items data
     */
    void showMoreItems(List<Item> items);
}
