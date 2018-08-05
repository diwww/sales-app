package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.ProductService;
import ru.gcsales.app.mapper.entity.ProductEntityDataMapper;
import ru.gcsales.app.mapper.entity.ShopEntityDataMapper;
import ru.gcsales.app.mapper.entity.ShopInfoEntityDataMapper;
import ru.gcsales.app.data.repository.ProductRepositoryImpl;
import ru.gcsales.app.data.repository.ShopRepositoryImpl;
import ru.gcsales.app.data.ShopService;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopRepository provideShopRepository(ShopService service,
                                                ShopEntityDataMapper shopEntityDataMapper,
                                                ShopInfoEntityDataMapper shopInfoEntityDataMapper) {
        return new ShopRepositoryImpl(service, shopEntityDataMapper, shopInfoEntityDataMapper);
    }

    @Provides
    @Singleton
    public ProductRepository provideProductRepository(ProductService service,
                                                      ProductEntityDataMapper mapper) {
        return new ProductRepositoryImpl(service, mapper);
    }
}
