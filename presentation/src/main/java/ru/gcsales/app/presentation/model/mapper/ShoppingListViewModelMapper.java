package ru.gcsales.app.presentation.model.mapper;

import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

/**
 * Mapper from shopping list domain model to shopping list view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class ShoppingListViewModelMapper extends AbstractMapper<ShoppingList, ShoppingListViewModel, Void> {

    private ItemViewModelMapper mItemViewModelMapper = new ItemViewModelMapper();

    @Override
    public ShoppingListViewModel transform(ShoppingList input, Void params) {
        ShoppingListViewModel shoppingListViewModel = null;

        if (input != null) {
            shoppingListViewModel = new ShoppingListViewModel();
            shoppingListViewModel.setId(input.getId());
            shoppingListViewModel.setName(input.getName());
            shoppingListViewModel.setItems(mItemViewModelMapper.transform(input.getItems(), null));
        }

        return shoppingListViewModel;
    }
}
