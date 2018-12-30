package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;

/**
 * Repository for obtaining shop data.
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
}
