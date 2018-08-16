package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListRepository extends TokenRepository {

    /**
     * Gets all shopping lists (their previews).
     *
     * @return {@link Observable} list of shopping lists.
     */
    Observable<List<ShoppingList>> getShoppingLists();

    /**
     * Gets a full version of the shopping list with the given id.
     *
     * @param id id of the shopping list to get
     * @return {@link Observable} of retrieved shopping list
     */
    Observable<ShoppingList> getShoppingList(long id);

    /**
     * Adds a new shopping list.
     *
     * @param name name of the shopping list
     * @return {@link Observable} of newly added shopping list.
     */
    Observable<ShoppingList> addShoppingList(String name);

    /**
     * Removes the shopping list with the given id.
     *
     * @param id id of the shopping list to remove
     * @return basically nothing
     */
    Observable<String> deletehoppingList(long id);

    /**
     * Adds the item to the shopping list.
     *
     * @param shoppingListId id of the shopping list
     * @param itemId         id of the item
     * @return response message, e.g. "OK"
     */
    Observable<String> addItem(long shoppingListId, long itemId);

    /**
     * Deletes the item from the shopping list.
     *
     * @param shoppingListId id of the shopping list
     * @param itemId         id of the item
     * @return response message, e.g. "OK"
     */
    Observable<String> deleteItem(long shoppingListId, long itemId);
}
