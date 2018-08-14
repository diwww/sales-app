package ru.gcsales.app.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.repository.AuthRepositoryImpl;
import ru.gcsales.app.data.repository.ShoppingListRepositoryImpl;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.data.service.ProductService;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;
import ru.gcsales.app.data.repository.ProductRepositoryImpl;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.data.service.ShopService;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopRepository provideShopRepository(ShopService service, AppDatabase database) {
        return new ShopRepositoryImpl(service, database);
    }

    @Provides
    @Singleton
    public ProductRepository provideProductRepository(ProductService service, AppDatabase database) {
        return new ProductRepositoryImpl(service, database);
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
}
