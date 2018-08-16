package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Use case for getting Products for given shop and category.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class GetProducts extends UseCase<List<Item>, GetProducts.Params> {

    private ShopRepository mShopRepository;

    public GetProducts(ShopRepository shopRepository,
                       PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShopRepository = shopRepository;
    }

    @Override
    Single<List<Item>> buildSingle(Params params) {
        return mShopRepository.getProducts(params.mShopId, params.mCategory, params.mPage);
    }

    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {

        private long mShopId;
        private int mPage;
        private String mCategory;

        public Params(long shopId, String category, int page) {
            mShopId = shopId;
            mPage = page;
            mCategory = category;
        }

        /**
         * Gets params instance.
         *
         * @param shopId   shop id
         * @param category category name
         * @param page     pagination page
         * @return new params instance
         */
        public static Params forShop(long shopId, String category, int page) {
            return new Params(shopId, category, page);
        }
    }
}
