package ru.gcsales.app.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ShopEntity;

/**
 * Shop DAO.
 *
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Dao
public interface ShopDAO {

    /**
     * Gets all shops.
     *
     * @return {@link Single} list of {@link ShopEntity} objects
     */
    @Query("SELECT * FROM shop")
    Single<List<ShopEntity>> get();

    /**
     * Inserts shops into db.
     *
     * @param entities list of shops to insertShoppingLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ShopEntity> entities);

    /**
     * Clears shops table.
     */
    @Query("DELETE FROM shop")
    void clearTable();
}
