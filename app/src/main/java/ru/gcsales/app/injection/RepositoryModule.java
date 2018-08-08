package ru.gcsales.app.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.repository.AuthRepositoryImpl;
import ru.gcsales.app.data.repository.TokenRepositoryImpl;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.data.service.ProductService;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.TokenRepository;
import ru.gcsales.app.data.repository.ProductRepositoryImpl;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.data.service.ShopService;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopRepository provideShopRepository(ShopService service) {
        return new ShopRepositoryImpl(service);
    }

    @Provides
    @Singleton
    public ProductRepository provideProductRepository(ProductService service) {
        return new ProductRepositoryImpl(service);
    }


    @Provides
    @Singleton
    public AuthRepository provideAuthRepository(AuthService service) {
        return new AuthRepositoryImpl(service);
    }

    @Provides
    @Singleton
    public TokenRepository provideTokenRepository(Context context) {
        return new TokenRepositoryImpl(context);
    }
}
