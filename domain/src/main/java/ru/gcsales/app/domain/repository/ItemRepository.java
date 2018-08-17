package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Item;

/**
 * Repository for getting item data.
 *
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public interface ItemRepository {
    /**
     * Get items for given shop, category and page.
     *
     * @param shopId   shop id
     * @param category category name
     * @param page     pagination page
     * @return {@link Observable} list of products
     */
    Observable<List<Item>> getItems(long shopId, String category, int page);
}
