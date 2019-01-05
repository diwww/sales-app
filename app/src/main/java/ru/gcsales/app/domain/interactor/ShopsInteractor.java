package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopsRepository;

/**
 * Shop interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class ShopsInteractor {

    private ShopsRepository mRepository;

    @Inject
    public ShopsInteractor(ShopsRepository mShopsRepository) {
        this.mRepository = mShopsRepository;
    }

    /**
     * Gets all available shops
     *
     * @return list of shops
     */
    public Maybe<List<Shop>> getShops() {
        return mRepository.getShops();
    }
}
