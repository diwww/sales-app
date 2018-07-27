package ru.gcsales.app.injection;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.repository.ShopRepository;

@Module
public class UseCaseModule {
    @Provides
    @Singleton
    public GetShops provideGetShops(ShopRepository shopRepository) {
        return new GetShops(shopRepository);
    }

    @Provides
    @Singleton
    public GetProducts provideGetProducts(ShopRepository shopRepository) {
        return new GetProducts(shopRepository);
    }
}
