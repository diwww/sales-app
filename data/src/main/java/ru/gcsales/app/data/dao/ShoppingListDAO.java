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
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
@Dao
public interface ShoppingListDAO {

    @Query("SELECT * FROM shopping_list")
    Single<List<ShoppingListEntity>> getShoppingLists();

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    ShoppingListEntity getShoppingList(long id);

    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shopping_list_item sli ON i.id = sli.item_id " +
            "INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE sli.shopping_list_id = :id")
    Single<List<ItemWithShop>> getShoppingListItems(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] addShoppingLists(List<ShoppingListEntity> shoppingListEntities);

    @Insert
    long addShoppingList(ShoppingListEntity entity);

    @Insert
    long[] addShoppingListItems(List<ShoppingListItemEntity> entityList);

    @Insert
    long addShoppingListItem(ShoppingListItemEntity entity);

    @Query("DELETE FROM shopping_list WHERE id = :id")
    void deleteShoppingList(long id);

    @Query("DELETE FROM shopping_list")
    void clearShoppingListTable();

    @Query("DELETE FROM shopping_list_item WHERE shopping_list_id = :shoppingListId")
    void clearShoppingListItemTable(long shoppingListId);

    @Query("DELETE FROM shopping_list_item WHERE shopping_list_id = :shoppingListId " +
            "AND item_id = :itemId")
    void deleteShoppingListItem(long shoppingListId, long itemId);

    @Query("DELETE FROM shopping_list_item")
    void clearShoppingListItemTable();
}
