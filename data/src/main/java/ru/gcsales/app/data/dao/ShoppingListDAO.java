package ru.gcsales.app.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListItemEntity;

/**
 * Shopping list DAO.
 *
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
@Dao
public interface ShoppingListDAO {

    /**
     * Gets all shopping lists.
     *
     * @return {@link Single} {@link ShoppingListEntity} object
     */
    @Query("SELECT * FROM shopping_list")
    Single<List<ShoppingListEntity>> getShoppingLists();

    /**
     * Gets the shopping list by id.
     *
     * @param id shopping list id
     * @return {@link ShoppingListEntity} object
     */
    @Query("SELECT * FROM shopping_list WHERE id = :id")
    ShoppingListEntity getShoppingList(long id);

    /**
     * Gets items for given shopping list.
     *
     * @param id shopping list id
     * @return {@link Single} list of {@link ItemWithShop} objects.
     */
    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shopping_list_item sli ON i.id = sli.item_id " +
            "INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE sli.shopping_list_id = :id")
    Single<List<ItemWithShop>> getItems(long id);

    /**
     * Inserts a shopping list to db.
     *
     * @param entity single shopping list entity to insertShoppingLists
     * @return id of inserted shopping list
     */
    @Insert
    long insertShoppingList(ShoppingListEntity entity);

    /**
     * Inserts shopping lists into db.
     *
     * @param entities list of shopping lists to insertShoppingLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingLists(List<ShoppingListEntity> entities);

    /**
     * Inserts an item into the shopping list.
     *
     * @param entity item to insertShoppingLists
     */
    @Insert
    void insertItem(ShoppingListItemEntity entity);

    /**
     * Inserts items into a shopping list.
     *
     * @param entities items to insertShoppingLists
     */
    @Insert
    void insertItems(List<ShoppingListItemEntity> entities);

    /**
     * Clears all relations between given shopping list and its items.
     *
     * @param shoppingListId shopping list id
     */
    @Query("DELETE FROM shopping_list_item WHERE shopping_list_id = :shoppingListId")
    void clearShoppingListItemTable(long shoppingListId);

    /**
     * Removes an item from shopping list
     *
     * @param shoppingListId shopping list id
     * @param itemId         items id
     */
    @Query("DELETE FROM shopping_list_item WHERE shopping_list_id = :shoppingListId " +
            "AND item_id = :itemId")
    void deleteShoppingListItem(long shoppingListId, long itemId);

    /**
     * Deletes a shopping list.
     *
     * @param id shopping list id
     */
    @Query("DELETE FROM shopping_list WHERE id = :id")
    void deleteShoppingList(long id);

    /**
     * Deletes all shopping lists.
     */
    @Query("DELETE FROM shopping_list")
    void deleteAllShoppingLists();
}
