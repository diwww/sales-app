package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.CategoryEntity;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Category entity mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class CategoryEntityMapper extends AbstractMapper<CategoryEntity, Category, Void> {


    @Override
    public Category transform(CategoryEntity input, Void params) {
        Category category = null;

        if (input != null) {
            category = new Category(input.getName());
        }

        return category;
    }
}
