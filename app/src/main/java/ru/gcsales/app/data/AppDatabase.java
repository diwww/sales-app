package ru.gcsales.app.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.gcsales.app.data.model.local.ProductEntity;
import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.domain.model.Shop;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Database(entities = {ShopEntity.class, ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopDAO getShopDAO();

    public abstract ProductDAO getProductDAO();
}
