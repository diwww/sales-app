package ru.gcsales.app.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.data.model.local.CategoryEntity;


/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM category WHERE shop_id = :shopId")
    Single<List<CategoryEntity>> getCategories(long shopId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<CategoryEntity> categoryEntities);

    @Query("DELETE FROM category")
    void clearTable();
}
