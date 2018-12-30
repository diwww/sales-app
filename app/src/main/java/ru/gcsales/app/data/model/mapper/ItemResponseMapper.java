package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.ItemEntity;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Item response mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ItemResponseMapper extends AbstractMapper<ItemResponse, ItemEntity, Void> {
    @Override
    public ItemEntity transform(ItemResponse input, Void params) {
        ItemEntity entity = null;

        if (input != null) {
            entity = new ItemEntity();
            entity.setId(input.getId());
            entity.setName(input.getName());
            entity.setCategory(input.getCategory());
            entity.setImageUrl(input.getImageUrl());
            entity.setCondition(input.getCondition());
            entity.setCrawlDate(input.getCrawlDate());
            entity.setDateIn(input.getDateIn());
            entity.setDateOut(input.getDateOut());
            entity.setDiscount(input.getDiscount());
            entity.setOldPrice(input.getOldPrice());
            entity.setNewPrice(input.getNewPrice());
            entity.setShopId(input.getShopResponse().getId());
        }

        return entity;
    }
}
