package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.dao.ShopDAO;
import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.data.model.mapper.ShopMapper;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.data.service.ShopService;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Implementation of {@link ShopRepository},
 * which gets data from internet and saves to database.
 * <p>
 * There are two scenarios:
 * <ol>
 * <li>If an internet connection is not available, then the data is fetched from a database
 * and returned.</li>
 * <li>If an internet connection is available, then the data is fetched from an internet, then
 * it is written to a database and finally it is fetched from a database and returned.</li>
 * </ol>
 * </p>
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopRepositoryImpl implements ShopRepository {

    private ShopService mShopService;
    private ShopDAO mShopDAO;
    private ShopMapper mShopMapper = new ShopMapper();

    public ShopRepositoryImpl(ShopService shopService, AppDatabase database) {
        mShopService = shopService;
        mShopDAO = database.getShopDAO();
    }

    @Override
    public Observable<List<Shop>> getShops() {
        // 1. Network scenario
        Single<List<ShopEntity>> remote = mShopService.getShops()
                .flatMap(responseList -> {
                    // Clear old local data
                    mShopDAO.clearTable();
                    // Insert new data from remote source
                    mShopDAO.insert(mShopMapper.transformResponse(responseList));
                    return mShopDAO.getShops();
                });

        // 2. Db scenario
        Single<List<ShopEntity>> local = mShopDAO.getShops();

        return Observable.concatArray(local.toObservable(), remote.toObservable())
                .map(mShopMapper::transformEntity);
    }
}
