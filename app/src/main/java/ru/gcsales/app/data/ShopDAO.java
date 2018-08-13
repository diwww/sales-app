package ru.gcsales.app.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.ShopEntity;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Dao
public interface ShopDAO {

    @Query("SELECT * FROM shop")
    Single<List<ShopEntity>> getShops();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<ShopEntity> shopEntities);

    @Query("DELETE FROM shop")
    void clearTable();
}
