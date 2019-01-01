package ru.gcsales.app.presentation.model.mapper;

import android.support.v7.view.menu.MenuView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;
import ru.gcsales.app.presentation.model.ItemViewModel;

/**
 * Mapper from item domain model to item view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public class ItemViewModelMapper extends AbstractMapper<Item, ItemViewModel, Void> {

    @Override
    public ItemViewModel transform(Item input, Void params) {
        ItemViewModel itemViewModel = null;

        if (input != null) {
            itemViewModel = new ItemViewModel();
            itemViewModel.setId(input.getId());
            itemViewModel.setName(input.getName());
            itemViewModel.setCategory(input.getCategory().getName());
            itemViewModel.setImageUrl(input.getImageUrl());
            itemViewModel.setOldPrice(input.getOldPrice());
            itemViewModel.setNewPrice(input.getNewPrice());

            // Cast the date to a pretty format
            String dateOut = new SimpleDateFormat("dd/MM", Locale.ENGLISH)
                    .format(input.getDateOut());
            itemViewModel.setDateOut(dateOut);
        }

        return itemViewModel;
    }
}
