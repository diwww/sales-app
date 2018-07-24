package ru.gcsales.app.domain.interactor;

import java.util.List;


import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Use case for shops retrieval.
 */
public class GetShops extends UseCase<List<Shop>> {

    private ShopRepository mShopRepository;

    public GetShops(ShopRepository shopRepository) {
        mShopRepository = shopRepository;
    }

    @Override
    public Observable<List<Shop>> execute() {
        return mShopRepository.getShops();
    }
}
