package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Shop;

/**
 * Shop repository
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public interface ShopsRepository {

    /**
     * Gets all shops
     *
     * @return {@link Maybe} with list of shops
     */
    Maybe<List<Shop>> getShops();

    /**
     * Gets shop by id
     *
     * @param id shop id
     * @return {@link Maybe} with the shop
     */
    Maybe<Shop> getShop(long id);
}
