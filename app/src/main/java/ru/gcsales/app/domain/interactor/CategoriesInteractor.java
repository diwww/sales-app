package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Category interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class CategoriesInteractor {

    private ShopRepository mRepository;

    @Inject
    public CategoriesInteractor(ShopRepository repository) {
        mRepository = repository;
    }

    /**
     * Gets categories for given shop
     *
     * @param shop shop
     * @return {@link Maybe} with list of categories
     */
    public Maybe<List<Category>> getCategories(Shop shop) {
        return mRepository.getCategories(shop);
    }
}
