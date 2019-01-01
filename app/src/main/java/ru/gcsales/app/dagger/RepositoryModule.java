package ru.gcsales.app.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.repository.AuthRepositoryImpl;
import ru.gcsales.app.data.repository.CategoryRepositoryImpl;
import ru.gcsales.app.data.repository.ItemRepositoryImpl;
import ru.gcsales.app.data.repository.ShoppingListRepositoryImpl;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.data.service.CategoryService;
import ru.gcsales.app.data.service.ItemService;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.CategoryRepository;
import ru.gcsales.app.domain.repository.ItemRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.data.service.ShopService;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Dagger module which provides repository implementations.
 *
 * @author Maxim Surovtsev
 * Created on 8/17/18
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopRepository provideShopRepository(ShopService service, AppDatabase database) {
        return new ShopRepositoryImpl(service, database);
    }

    @Provides
    @Singleton
    public ItemRepository provideProductRepository(ItemService service, AppDatabase database) {
        return new ItemRepositoryImpl(service, database);
    }

    @Provides
    @Singleton
    public AuthRepository provideAuthRepository(Context context, AuthService service) {
        return new AuthRepositoryImpl(context, service);
    }

    @Provides
    @Singleton
    public ShoppingListRepository provideShoppingListRepository(Context context,
                                                                ShoppingListService service,
                                                                AppDatabase database) {
        return new ShoppingListRepositoryImpl(context, service, database);
    }

    @Provides
    @Singleton
    public CategoryRepository provideCategoryRepository(CategoryService service,
                                                        AppDatabase database) {
        return new CategoryRepositoryImpl(service, database);
    }
}
