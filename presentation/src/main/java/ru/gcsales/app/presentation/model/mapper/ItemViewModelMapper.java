package ru.gcsales.app.presentation.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.model.ItemViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public class ItemViewModelMapper {


    public ItemViewModel transform(Item item) {
        ItemViewModel itemViewModel = null;

        if (item != null) {
            itemViewModel = new ItemViewModel();
            itemViewModel.setId(item.getId());
            itemViewModel.setName(item.getName());
            itemViewModel.setCategory(item.getCategory());
            itemViewModel.setImageUrl(item.getImageUrl());
            itemViewModel.setCondition(item.getCondition());
            itemViewModel.setDateIn(item.getDateIn());
            itemViewModel.setDateOut(item.getDateOut());
            itemViewModel.setDiscount(item.getDiscount());
            itemViewModel.setOldPrice(item.getOldPrice());
            itemViewModel.setNewPrice(item.getNewPrice());
        }

        return itemViewModel;
    }

    public List<ItemViewModel> transform(List<Item> items) {
        List<ItemViewModel> itemViewModels;

        if (items != null && !items.isEmpty()) {
            itemViewModels = new ArrayList<>();
            for (Item item : items) {
                itemViewModels.add(transform(item));
            }
        } else {
            return Collections.emptyList();
        }

        return itemViewModels;
    }
}
