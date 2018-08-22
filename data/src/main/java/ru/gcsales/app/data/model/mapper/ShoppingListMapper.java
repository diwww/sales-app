package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.remote.ShoppingListResponse;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
public class ShoppingListMapper {

    public ShoppingListEntity transformResponse(ShoppingListResponse response) {
        ShoppingListEntity entity = null;

        if (response != null) {
            entity = new ShoppingListEntity();
            entity.setId(response.getId());
            entity.setName(response.getName());
        }

        return entity;
    }

    public List<ShoppingListEntity> transformResponse(List<ShoppingListResponse> responses) {
        List<ShoppingListEntity> entities;

        if (responses != null && !responses.isEmpty()) {
            entities = new ArrayList<>();
            for (ShoppingListResponse response : responses) {
                entities.add(transformResponse(response));
            }
        } else {
            return Collections.emptyList();
        }

        return entities;
    }

    public ShoppingList transformEntity(ShoppingListEntity entity) {
        ShoppingList shoppingList = null;

        if (entity != null) {
            shoppingList = new ShoppingList();
            shoppingList.setId(entity.getId());
            shoppingList.setName(entity.getName());
        }

        return shoppingList;
    }

    public List<ShoppingList> transformEntity(List<ShoppingListEntity> entities) {
        List<ShoppingList> shoppingLists;

        if (entities != null && !entities.isEmpty()) {
            shoppingLists = new ArrayList<>();
            for (ShoppingListEntity entity : entities) {
                shoppingLists.add(transformEntity(entity));
            }
        } else {
            return Collections.emptyList();
        }

        return shoppingLists;
    }
}
