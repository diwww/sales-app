package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Category;

/**
 * Repository for getting categories for given shop.
 *
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoryRepository {

    /**
     * Gets available categories.
     *
     * @param shopId shop id
     * @return {@link List} of categories
     */
    Observable<List<Category>> getCategories(long shopId);
}
