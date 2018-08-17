package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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

    @Inject
    public GetShops(ShopRepository shopRepository,
                    PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShopRepository = shopRepository;
    }

    @Override
    public Observable<List<Shop>> buildObservable(Void params) {
        return mShopRepository.getShops();
    }
}
