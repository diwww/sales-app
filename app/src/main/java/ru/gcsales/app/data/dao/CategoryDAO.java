package ru.gcsales.app.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.CategoryEntity;


/**
 * Category DAO.
 *
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
@Dao
public interface CategoryDAO {

    /**
     * Gets all categories for given shop.
     *
     * @param shopId shop id
     * @return {@link Single} list of {@link CategoryEntity} objects
     */
    @Query("SELECT * FROM category WHERE shop_id = :shopId")
    Single<List<CategoryEntity>> get(long shopId);

    /**
     * Inserts categories into db.
     *
     * @param entities list of categories to insertShoppingLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CategoryEntity> entities);

    /**
     * Clears categories table.
     */
    @Query("DELETE FROM category")
    void clearTable();
}
