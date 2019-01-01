package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.Shop;

/**
 * Shop repository
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public interface ShopRepository {

    /**
     * Gets all shops
     *
     * @return {@link Maybe} with list of shops
     */
    Maybe<List<Shop>> getShops();

    /**
     * Gets categories for given shop
     *
     * @param shop shop
     * @return {@link Maybe} with list of categories
     */
    Maybe<List<Category>> getCategories(Shop shop);

    /**
     * Gets items for given shop, category and page
     *
     * @param shop     shop
     * @param category category
     * @param page     pagination page number
     * @return {@link Maybe} with list of items
     */
    Maybe<List<Item>> getItems(Shop shop, Category category, int page);
}
