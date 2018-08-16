package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.domain.model.Item;
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
     * @return {@link Single} list of shops
     */
    Single<List<Shop>> getShops();

    /**
     * Get products for given shop, category and page.
     *
     * @param shopId   shop id
     * @param category category name
     * @param page     pagination page
     * @return {@link Single} list of products
     */
    Single<List<Item>> getProducts(long shopId, String category, int page);
}
