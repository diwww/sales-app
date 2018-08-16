package ru.gcsales.app.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.gcsales.app.data.model.local.ItemEntity;
import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListProductEntity;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Database(entities = {ShopEntity.class, ItemEntity.class, ShoppingListEntity.class, ShoppingListProductEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopDAO getShopDAO();

    public abstract ItemDAO getItemDAO();

    public abstract ShoppingListDAO getShoppingListDAO();
}
