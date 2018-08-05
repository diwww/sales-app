package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.mapper.entity.ShopEntityDataMapper;
import ru.gcsales.app.data.ShopService;
import ru.gcsales.app.mapper.entity.ShopInfoEntityDataMapper;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.ShopInfo;
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
    private ShopInfoEntityDataMapper mShopInfoEntityDataMapper;

    public ShopRepositoryImpl(ShopService shopService,
                              ShopEntityDataMapper shopEntityDataMapper,
                              ShopInfoEntityDataMapper shopInfoEntityDataMapper) {
        mShopService = shopService;
        mShopEntityDataMapper = shopEntityDataMapper;
        mShopInfoEntityDataMapper = shopInfoEntityDataMapper;
    }

    @Override
    public Observable<List<Shop>> getShops() {
        return mShopService.getShops().map(mShopEntityDataMapper::transform);
    }

    @Override
    public Observable<ShopInfo> getShopInfo(long id) {
        return mShopService.getShopInfo(id).map(mShopInfoEntityDataMapper::transform);
    }
}
