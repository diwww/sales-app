package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.data.entity.ProductsResponse;
import ru.gcsales.app.data.mapper.ProductEntityDataMapper;
import ru.gcsales.app.data.mapper.ShopEntityDataMapper;
import ru.gcsales.app.data.ShopService;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Implementation of {@link ShopRepository},
 * which gets data from internet.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopRepositoryImpl implements ShopRepository {

    private ShopService mShopService;
    private ShopEntityDataMapper mShopEntityDataMapper;
    private ProductEntityDataMapper mProductEntityDataMapper;

    public ShopRepositoryImpl(ShopService shopService, ShopEntityDataMapper shopEntityDataMapper,
                              ProductEntityDataMapper productEntityDataMapper) {
        mShopService = shopService;
        mShopEntityDataMapper = shopEntityDataMapper;
        mProductEntityDataMapper = productEntityDataMapper;
    }

    @Override
    public Observable<List<Shop>> getShops() {
        return mShopService.getShops().map(mShopEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Product>> getShop(long id) {
        return mShopService.getShopProducts(id, 1)
                .map(ProductsResponse::getProductEntities)
                .map(mProductEntityDataMapper::transform);
    }
}
