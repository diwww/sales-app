package ru.gcsales.app.presentation.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;
import ru.gcsales.app.presentation.model.CategoryViewModel;

/**
 * Mapper from category domain model to category view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class CategoryViewModelMapper extends AbstractMapper<Category, CategoryViewModel, Void> {

    @Override
    public CategoryViewModel transform(Category input, Void params) {
        CategoryViewModel categoryViewModel = null;

        if (input != null) {
            categoryViewModel = new CategoryViewModel(input.getName());
        }

        return categoryViewModel;
    }
}
