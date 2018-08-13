package ru.gcsales.app.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ProductEntity;
import ru.gcsales.app.data.model.local.ProductWithShop;

/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
@Dao
public interface ProductDAO {

    @Query("SELECT p.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM product p INNER JOIN shop s ON p.shop_id = s.id " +
            "WHERE shop_id = :shopId " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ProductWithShop>> getProductsWithShops(long shopId, int page);

    @Query("SELECT p.*, s.alias shop_alias, s.name shop_name, s.image_url shop_image_url " +
            "FROM product p INNER JOIN shop s ON p.shop_id = s.id " +
            "WHERE shop_id = :shopId AND category = :category " +
            "LIMIT 30 OFFSET 30 * (:page - 1)")
    Single<List<ProductWithShop>> getProductsWithShops(long shopId, String category, int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<ProductEntity> productEntities);

    @Query("DELETE FROM product")
    void clearTable();
}
