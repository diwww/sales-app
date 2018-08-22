package ru.gcsales.app.presentation.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.presentation.model.CategoryViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class CategoryViewModelMapper {

    public CategoryViewModel transform(Category category) {
        CategoryViewModel categoryViewModel = null;

        if (category != null) {
            categoryViewModel = new CategoryViewModel(category.getName());
        }

        return categoryViewModel;
    }

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
