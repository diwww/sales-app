package ru.gcsales.app.presentation.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class ShoppingListViewModelMapper {

    ItemViewModelMapper mMapper = new ItemViewModelMapper();

    public ShoppingListViewModel transform(ShoppingList shoppingList) {
        ShoppingListViewModel shoppingListViewModel = null;

        if (shoppingList != null) {
            shoppingListViewModel = new ShoppingListViewModel();
            shoppingListViewModel.setId(shoppingList.getId());
            shoppingListViewModel.setName(shoppingList.getName());
            shoppingListViewModel.setItems(mMapper.transform(shoppingList.getItems()));
        }

        return shoppingListViewModel;
    }

    public List<ShoppingListViewModel> transform(List<ShoppingList> shoppingLists) {
        List<ShoppingListViewModel> shoppingListViewModels;

        if (shoppingLists != null && !shoppingLists.isEmpty()) {
            shoppingListViewModels = new ArrayList<>();
            for (ShoppingList shoppingList : shoppingLists) {
                shoppingListViewModels.add(transform(shoppingList));
            }
        } else {
            return Collections.emptyList();
        }

        return shoppingListViewModels;
    }
}
