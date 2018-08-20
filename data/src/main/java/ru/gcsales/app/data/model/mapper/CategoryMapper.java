package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.CategoryEntity;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class CategoryMapper {

    public CategoryEntity transform(String categoryResponse, long shopId) {
        CategoryEntity entity = null;

        if (categoryResponse != null) {
            entity = new CategoryEntity();
            entity.setName(categoryResponse);
            entity.setShopId(shopId);
        }

        return entity;
    }

    public List<CategoryEntity> transform(List<String> categoryResponseList, long shopId) {
        List<CategoryEntity> entityList;

        if (categoryResponseList != null && !categoryResponseList.isEmpty()) {
            entityList = new ArrayList<>();
            for (String response : categoryResponseList) {
                entityList.add(transform(response, shopId));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }

    public String transform(CategoryEntity entity) {
        String categoryString = null;

        if (entity != null) {
            categoryString = entity.getName();
        }

        return categoryString;
    }

    public List<String> transform(List<CategoryEntity> categoryEntityList) {
        List<String> categoryStringList;

        if (categoryEntityList != null && !categoryEntityList.isEmpty()) {
            categoryStringList = new ArrayList<>();
            for (CategoryEntity entity : categoryEntityList) {
                categoryStringList.add(transform(entity));
            }
        } else {
            return Collections.emptyList();
        }

        return categoryStringList;
    }
}
