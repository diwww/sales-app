package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Use case for getting shops.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class GetShops extends UseCase<List<Shop>, Void> {

    private ShopRepository mShopRepository;

    public GetShops(ShopRepository shopRepository,
                    PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShopRepository = shopRepository;
    }

    @Override
    public Single<List<Shop>> buildSingle(Void params) {
        return mShopRepository.getShops();
    }
}
