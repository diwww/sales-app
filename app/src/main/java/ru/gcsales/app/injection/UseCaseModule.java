package ru.gcsales.app.injection;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;
import ru.gcsales.app.domain.repository.TokenRepository;

// TODO: make Activity scope
// (баг с переключением магазинов)
@Module
public class UseCaseModule {
    @Provides
//    @Singleton
    public GetShops provideGetShops(ShopRepository shopRepository) {
        return new GetShops(shopRepository);
    }

    @Provides
//    @Singleton
    public GetProducts provideGetProducts(ProductRepository productRepository) {
        return new GetProducts(productRepository);
    }

    @Provides
//    @Singleton
    public GetShopInfo provideGetShopInfo(ShopRepository shopRepository) {
        return new GetShopInfo(shopRepository);
    }

    @Provides
    public Login provideLogin(AuthRepository authRepository, TokenRepository tokenRepository) {
        return new Login(authRepository, tokenRepository);
    }
}
