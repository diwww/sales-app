package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class GetProducts extends UseCase<List<Product>, GetProducts.Params> {

    private ShopRepository mShopRepository;

    public GetProducts(ShopRepository shopRepository) {
        mShopRepository = shopRepository;
    }

    @Override
    Observable<List<Product>> buildObservable(Params params) {
        return mShopRepository.getShop(params.shopId);
    }

    public static class Params {

        private long shopId;

        public Params(long shopId) {
            this.shopId = shopId;
        }

        public static Params forShop(long shopId) {
            return new Params(shopId);
        }
    }
}
