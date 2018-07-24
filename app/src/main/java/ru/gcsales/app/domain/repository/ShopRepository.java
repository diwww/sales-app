package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;

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

    // TODO: get concrete shop data
}
