package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.data.service.ShopService;
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

    public ShopRepositoryImpl(ShopService shopService) {
        mShopService = shopService;
    }

    @Override
    public Observable<List<Shop>> getShops() {
        return mShopService.getShops();
    }

    @Override
    public Observable<ShopInfo> getShopInfo(long id) {
        return mShopService.getShopInfo(id);
    }
}
