package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.repository.ProductRepository;

/**
 * Use case for getting products for given shop and category.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class GetProducts extends UseCase<List<Product>, GetProducts.Params> {

    private ProductRepository mProductRepository;

    public GetProducts(ProductRepository productRepository) {
        mProductRepository = productRepository;
    }

    @Override
    Observable<List<Product>> buildObservable(Params params) {
        return mProductRepository.getProducts(params.mShopId, params.mCategory, params.mPage);
    }

    public static class Params {

        private long mShopId;
        private int mPage;
        private String mCategory;

        public Params(long shopId, String category, int page) {
            mShopId = shopId;
            mPage = page;
            mCategory = category;
        }

        public static Params forShop(long shopId, String category, int page) {
            return new Params(shopId, category, page);
        }
    }
}
