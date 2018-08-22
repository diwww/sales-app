package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.repository.CategoryRepository;

/**
 * Use case for getting available categories for a given shop.
 *
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class GetCategories extends UseCase<List<Category>, GetCategories.Params> {

    private CategoryRepository mCategoryRepository;

    @Inject
    public GetCategories(CategoryRepository categoryRepository,
                         PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mCategoryRepository = categoryRepository;
    }

    @Override
    Observable<List<Category>> buildObservable(Params params) {
        return mCategoryRepository.getCategories(params.mShopId);
    }

    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {
        private long mShopId;

        public Params(long shopId) {
            mShopId = shopId;
        }

        /**
         * Gets params instance.
         *
         * @param shopId shop id
         * @return new params instance
         */
        public static Params get(long shopId) {
            return new Params(shopId);
        }
    }

}
