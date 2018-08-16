package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * Repository for working with a shopping list data.
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListRepository extends TokenRepository {

    /**
     * Gets all shopping lists (their previews).
     *
     * @return {@link Single} list of shopping lists.
     */
    Single<List<ShoppingList>> getShoppingLists();

    /**
     * Gets a full version of the shopping list with the given id.
     *
     * @param id id of the shopping list to get
     * @return {@link Single} of retrieved shopping list
     */
    Single<ShoppingList> getShoppingList(long id);

    /**
     * Adds a new shopping list.
     *
     * @param name name of the shopping list
     * @return {@link Single} of newly added shopping list.
     */
    Single<ShoppingList> addShoppingList(String name);

    /**
     * Removes the shopping list with the given id.
     *
     * @param id id of the shopping list to remove
     * @return response message, e.g. "OK"
     */
    Single<String> deleteShoppingList(long id);

    /**
     * Adds the item to the shopping list.
     *
     * @param shoppingListId id of the shopping list
     * @param itemId         id of the item
     * @return response message, e.g. "OK"
     */
    Single<String> addItem(long shoppingListId, long itemId);

    /**
     * Deletes the item from the shopping list.
     *
     * @param shoppingListId id of the shopping list
     * @param itemId         id of the item
     * @return response message, e.g. "OK"
     */
    Single<String> deleteItem(long shoppingListId, long itemId);
}
