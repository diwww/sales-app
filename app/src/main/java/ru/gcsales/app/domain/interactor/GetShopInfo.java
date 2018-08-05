package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Use case for getting info for given shop.
 *
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class GetShopInfo extends UseCase<ShopInfo, GetShopInfo.Params> {

    private ShopRepository mShopRepository;

    public GetShopInfo(ShopRepository shopRepository) {
        mShopRepository = shopRepository;
    }

    @Override
    Observable<ShopInfo> buildObservable(Params params) {
        return mShopRepository.getShopInfo(params.mShopId);
    }

    public static class Params {

        private long mShopId;

        public Params(long shopId) {
            mShopId = shopId;
        }

        public static Params forShop(long shopId) {
            return new Params(shopId);
        }
    }
}
