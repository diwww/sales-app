package ru.gcsales.app.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ItemEntity;
import ru.gcsales.app.data.model.local.ItemWithShop;

/**
 * Item DAO.
 *
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
@Dao
public interface ItemDAO {

    /**
     * Gets items for given shop and page.
     *
     * @param shopId shop id
     * @param page   page number for pagination
     * @return {@link Single} list of {@link ItemWithShop} objects
     */
    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE shop_id = :shopId " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ItemWithShop>> get(long shopId, int page);

    /**
     * Gets items for given shop and page filtered by a category.
     *
     * @param shopId   shop id
     * @param category category name
     * @param page     page number for pagintion
     * @return {@link Single} list of {@link ItemWithShop} objects
     */
    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE shop_id = :shopId AND category = :category " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ItemWithShop>> get(long shopId, String category, int page);

    /**
     * Inserts items into db.
     *
     * @param entities list of items to insertShoppingLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ItemEntity> entities);

    /**
     * Clears items table.
     */
    @Query("DELETE FROM item")
    void clearTable();
}
