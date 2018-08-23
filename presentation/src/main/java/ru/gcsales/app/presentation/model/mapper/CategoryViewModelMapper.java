package ru.gcsales.app.presentation.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.presentation.model.CategoryViewModel;

/**
 * Mapper from category domain model to category view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class CategoryViewModelMapper {

    /**
     * Transforms a single model.
     *
     * @param category domain model
     * @return view model
     */
    public CategoryViewModel transform(Category category) {
        CategoryViewModel categoryViewModel = null;

        if (category != null) {
            categoryViewModel = new CategoryViewModel(category.getName());
        }

        return categoryViewModel;
    }

    /**
     * Transforms a list of models.
     *
     * @param categories domain model list
     * @return view model list
     */
    public List<CategoryViewModel> transform(List<Category> categories) {

        List<CategoryViewModel> categoryViewModels;

        if (categories != null && !categories.isEmpty()) {
            categoryViewModels = new ArrayList<>();
            for (Category category : categories) {
                categoryViewModels.add(transform(category));
            }
        } else {
            return Collections.emptyList();
        }

        return categoryViewModels;
    }
}
