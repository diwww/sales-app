package ru.gcsales.app.domain.interactor;

import java.awt.print.PrinterAbortException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.CategoryRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class GetCategories extends UseCase<List<String>, GetCategories.Params> {

    private CategoryRepository mCategoryRepository;

    @Inject
    public GetCategories(CategoryRepository categoryRepository,
                         PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mCategoryRepository = categoryRepository;
    }

    @Override
    Observable<List<String>> buildObservable(Params params) {
        return mCategoryRepository.getCategories(params.mShopId);
    }

    public static class Params {
        private long mShopId;

        public Params(long shopId) {
            mShopId = shopId;
        }

        public static Params get(long shopId) {
            return new Params(shopId);
        }
    }

}
