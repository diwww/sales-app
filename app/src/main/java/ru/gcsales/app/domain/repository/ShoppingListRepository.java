package ru.gcsales.app.domain.repository;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingListEntry;

/**
 * Shopping list repository
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListRepository extends TokenRepository {

    /**
     * Gets the shopping list
     *
     * @return {@link Maybe} with shopping list entries
     */
    Maybe<List<ShoppingListEntry>> getEntries();

    /**
     * Creates a new shopping list entry
     *
     * @param item item to add to the shopping list
     * @return {@link Maybe} with new shopping list entry
     */
    Maybe<ShoppingListEntry> newEntry(Item item);

    /**
     * Increments the quantity of the entry
     *
     * @param entry shopping list entry
     * @return {@link Completable} with the result of increment
     */
    Completable incrementQuantity(ShoppingListEntry entry);

    /**
     * Decrements the quantity of the entry
     *
     * @param entry shopping list entry
     * @return {@link Completable} with the result of decrement
     */
    Completable decrementQuantity(ShoppingListEntry entry);

    /**
     * Removes the entry from the shopping list
     *
     * @param entry shopping list entry
     * @return {@link Completable} with the result of removal
     */
    Completable removeEntry(ShoppingListEntry entry);
}
