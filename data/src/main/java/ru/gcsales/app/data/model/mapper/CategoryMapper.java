package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.CategoryEntity;
import ru.gcsales.app.domain.model.Category;

/**
 * Category mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class CategoryMapper {

    /**
     * Transforms network response to db entity.
     *
     * @param response network response
     * @param shopId   shop id
     * @return {@link CategoryEntity} object
     */
    public CategoryEntity transform(String response, long shopId) {
        CategoryEntity entity = null;

        if (response != null) {
            entity = new CategoryEntity();
            entity.setName(response);
            entity.setShopId(shopId);
        }

        return entity;
    }

    /**
     * Transforms a list of network responses to a list of db entities.
     *
     * @param responses list of db responses
     * @param shopId    shop id
     * @return list of {@link CategoryEntity} objects
     */
    public List<CategoryEntity> transform(List<String> responses, long shopId) {
        List<CategoryEntity> entities;

        if (responses != null && !responses.isEmpty()) {
            entities = new ArrayList<>();
            for (String response : responses) {
                entities.add(transform(response, shopId));
            }
        } else {
            return Collections.emptyList();
        }

        return entities;
    }

    /**
     * Transform
     * @param entity
     * @return
     */
    public Category transform(CategoryEntity entity) {
        Category category = null;

        if (entity != null) {
            category = new Category(entity.getName());
        }

        return category;
    }

    public List<Category> transform(List<CategoryEntity> entities) {
        List<Category> categories;

        if (entities != null && !entities.isEmpty()) {
            categories = new ArrayList<>();
            for (CategoryEntity entity : entities) {
                categories.add(transform(entity));
            }
        } else {
            return Collections.emptyList();
        }

        return categories;
    }
}
