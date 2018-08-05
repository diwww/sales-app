package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.ShopInfo;

/**
 * Interface for obtaining shop data.
 * This must be implemented in a data layer.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public interface ShopRepository {

    /**
     * Gets all shops.
     *
     * @return {@link Observable} list of shops
     */
    Observable<List<Shop>> getShops();

    /**
     * Gets shop info.
     *
     * @param id id of a shop
     * @return {@link Observable} of shop info object
     */
    Observable<ShopInfo> getShopInfo(long id);
}
