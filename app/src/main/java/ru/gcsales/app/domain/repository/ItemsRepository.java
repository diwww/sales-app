package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Item;

/**
 * Item repository
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public interface ItemsRepository {

    /**
     * Gets items for given shop
     *
     * @param id id of the shop
     * @return {@link Maybe} with list of items
     */
    Maybe<List<Item>> getItems(long id);
}
