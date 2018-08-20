package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.service.CategoryService;
import ru.gcsales.app.domain.repository.CategoryRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryService mCategoryService;

    public CategoryRepositoryImpl(CategoryService categoryService, AppDatabase database) {
        mCategoryService = categoryService;
    }

    @Override
    public Observable<List<String>> getCategories(long shopId) {
        return mCategoryService.getCategories(shopId).toObservable();
    }
}
