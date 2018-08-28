package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Shopping list entity mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShoppingListEntityMapper extends AbstractMapper<ShoppingListEntity, ShoppingList, Void> {
    @Override
    public ShoppingList transform(ShoppingListEntity input, Void params) {
        ShoppingList shoppingList = null;

        if (input != null) {
            shoppingList = new ShoppingList();
            shoppingList.setId(input.getId());
            shoppingList.setName(input.getName());
        }

        return shoppingList;
    }
}
