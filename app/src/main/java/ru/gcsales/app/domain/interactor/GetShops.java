package ru.gcsales.app.domain.interactor;

import java.util.List;


import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Use case for shops retrieval.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class GetShops extends UseCase<List<Shop>, Void> {

    private ShopRepository mShopRepository;

    public GetShops(ShopRepository shopRepository) {
        mShopRepository = shopRepository;
    }

    @Override
    public Observable<List<Shop>> buildObservable(Void params) {
        return mShopRepository.getShops();
    }
}
