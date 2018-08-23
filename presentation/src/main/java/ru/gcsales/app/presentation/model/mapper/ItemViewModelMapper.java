package ru.gcsales.app.presentation.model.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.model.ItemViewModel;

/**
 * Mapper from item domain model to item view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public class ItemViewModelMapper {


    /**
     * Transforms a single model.
     *
     * @param item domain model
     * @return view model
     */
    public ItemViewModel transform(Item item) {
        ItemViewModel itemViewModel = null;

        if (item != null) {
            itemViewModel = new ItemViewModel();
            itemViewModel.setId(item.getId());
            itemViewModel.setName(item.getName());
            itemViewModel.setCategory(item.getCategory());
            itemViewModel.setImageUrl(item.getImageUrl());
            itemViewModel.setOldPrice(item.getOldPrice());
            itemViewModel.setNewPrice(item.getNewPrice());

            // Cast the date to a pretty format
            String dateOut = new SimpleDateFormat("dd/MM", Locale.ENGLISH)
                    .format(item.getDateOut());
            itemViewModel.setDateOut(dateOut);
        }

        return itemViewModel;
    }

    /**
     * Transforms a list of models.
     *
     * @param items domain model list
     * @return view model list
     */
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
