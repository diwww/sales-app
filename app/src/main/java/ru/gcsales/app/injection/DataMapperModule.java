package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.presentation.mvp.mapper.ShopModelDataMapper;

@Module
public class DataMapperModule {

    @Provides
    @Singleton
    public ShopModelDataMapper provideShopModelDataMapper() {
        return new ShopModelDataMapper();
    }
}
