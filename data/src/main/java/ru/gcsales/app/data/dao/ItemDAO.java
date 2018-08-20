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
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
@Dao
public interface ItemDAO {

    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE shop_id = :shopId " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ItemWithShop>> getItems(long shopId, int page);

    @Query("SELECT i.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM item i INNER JOIN shop s ON i.shop_id = s.id " +
            "WHERE shop_id = :shopId AND category = :category " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ItemWithShop>> getItems(long shopId, String category, int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<ItemEntity> productEntities);

    @Query("DELETE FROM item")
    void clearTable();
}
