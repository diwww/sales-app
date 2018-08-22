package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.dao.CategoryDAO;
import ru.gcsales.app.data.model.local.CategoryEntity;
import ru.gcsales.app.data.model.mapper.CategoryMapper;
import ru.gcsales.app.data.service.CategoryService;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.repository.CategoryRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryService mCategoryService;
    private CategoryDAO mCategoryDAO;
    private CategoryMapper mCategoryMapper = new CategoryMapper();

    public CategoryRepositoryImpl(CategoryService categoryService, AppDatabase database) {
        mCategoryService = categoryService;
        mCategoryDAO = database.getCategoryDAO();
    }

    @Override
    public Observable<List<Category>> getCategories(long shopId) {
        // 1. Network scenario
        Single<List<CategoryEntity>> remote = mCategoryService.getCategories(shopId)
                .flatMap(responseList -> {
                    // Delete old local data
                    mCategoryDAO.clearTable();
                    // Insert fresh data from network to db
                    mCategoryDAO.insert(mCategoryMapper.transform(responseList, shopId));
                    return mCategoryDAO.get(shopId);
                });

        // 2. Db scenario
        Single<List<CategoryEntity>> local = mCategoryDAO.get(shopId);

        return Observable.concatArray(local.toObservable(), remote.toObservable())
                .map(mCategoryMapper::transform);
    }
}
