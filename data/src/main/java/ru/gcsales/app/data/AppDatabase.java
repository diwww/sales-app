package ru.gcsales.app.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.gcsales.app.data.dao.CategoryDAO;
import ru.gcsales.app.data.dao.ItemDAO;
import ru.gcsales.app.data.dao.ShopDAO;
import ru.gcsales.app.data.dao.ShoppingListDAO;
import ru.gcsales.app.data.model.local.CategoryEntity;
import ru.gcsales.app.data.model.local.ItemEntity;
import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListItemEntity;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Database(entities = {ShopEntity.class, ItemEntity.class, ShoppingListEntity.class,
        ShoppingListItemEntity.class, CategoryEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ShopDAO getShopDAO();

    public abstract ItemDAO getItemDAO();

    public abstract ShoppingListDAO getShoppingListDAO();

    public abstract CategoryDAO getCategoryDAO();
}
