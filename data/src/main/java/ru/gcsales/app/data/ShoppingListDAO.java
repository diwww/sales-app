package ru.gcsales.app.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListProductEntity;

/**
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
@Dao
public interface ShoppingListDAO {

    @Insert
    long addShoppingList(ShoppingListEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] addShoppingLists(List<ShoppingListEntity> shoppingListEntities);

    @Query("SELECT * FROM shopping_list")
    Single<List<ShoppingListEntity>> getShoppingLists();

    @Insert
    long[] addShoppingListProducts(List<ShoppingListProductEntity> entityList);

    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shopping_list_product slp ON i.id = slp.product_id " +
            "INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE slp.shopping_list_id = :id")
    Single<List<ItemWithShop>> getShoppingListProducts(long id);

    @Query("DELETE FROM shopping_list")
    void clearShoppingListTable();

    @Query("DELETE FROM shopping_list_product")
    void clearShoppingListProductTable();

    @Query("DELETE FROM shopping_list_product WHERE shopping_list_id = :id")
    void clearShoppingListProductTable(long id);

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    ShoppingListEntity getShoppingList(long id);
}
