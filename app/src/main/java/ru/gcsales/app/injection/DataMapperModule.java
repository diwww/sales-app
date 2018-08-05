package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.mapper.entity.ProductEntityDataMapper;
import ru.gcsales.app.mapper.entity.ShopEntityDataMapper;
import ru.gcsales.app.mapper.entity.ShopInfoEntityDataMapper;
import ru.gcsales.app.mapper.model.ProductModelDataMapper;
import ru.gcsales.app.mapper.model.ShopInfoModelDataMapper;
import ru.gcsales.app.mapper.model.ShopModelDataMapper;

@Module
public class DataMapperModule {

    @Provides
    @Singleton
    public ShopModelDataMapper provideShopModelDataMapper() {
        return new ShopModelDataMapper();
    }

    @Provides
    @Singleton
    public ShopEntityDataMapper provideShopEntityDataMapper() {
        return new ShopEntityDataMapper();
    }

    @Provides
    @Singleton
    public ProductModelDataMapper provideProductModelDataMapper() {
        return new ProductModelDataMapper();
    }

    @Provides
    @Singleton
    public ProductEntityDataMapper provideProductEntityDataMapper() {
        return new ProductEntityDataMapper();
    }


    @Provides
    @Singleton
    public ShopInfoModelDataMapper provideShopInfoModelDataMapper(ShopModelDataMapper mapper) {
        return new ShopInfoModelDataMapper(mapper);
    }

    @Provides
    @Singleton
    public ShopInfoEntityDataMapper provideShopInfoEntityDataMapper(ShopEntityDataMapper mapper) {
        return new ShopInfoEntityDataMapper(mapper);
    }

}
