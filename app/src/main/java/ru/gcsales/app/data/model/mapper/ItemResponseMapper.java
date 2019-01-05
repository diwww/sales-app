package ru.gcsales.app.data.model.mapper;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Item response mapper
 *
 * @author Maxim Surovtsev
 * @since 06/01/2019
 */
public class ItemResponseMapper extends AbstractMapper<ItemResponse, Item, Void> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Item transform(ItemResponse input, Void params) throws Exception {
        return new Item()
                .setImageUrl(input.getImageUrl())
                .setNewPrice(input.getNewPrice())
                .setOldPrice(input.getOldPrice())
                .setName(input.getName())
                .setConditions(input.getConditions())
                .setDiscount(input.getDiscount())
                .setId(input.getId())
                .setTill(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(input.getTill()));
    }
}
