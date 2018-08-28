package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.remote.ShoppingListResponse;

/**
 * Shopping list response mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShoppingListResponseMapper
        extends AbstractMapper<ShoppingListResponse, ShoppingListEntity, Void> {
    @Override
    public ShoppingListEntity transform(ShoppingListResponse input, Void params) {
        ShoppingListEntity entity = null;

        if (input != null) {
            entity = new ShoppingListEntity();
            entity.setId(input.getId());
            entity.setName(input.getName());
        }

        return entity;
    }
}
