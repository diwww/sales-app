package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.data.model.remote.ShopResponse;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Shop response mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShopResponseMapper extends AbstractMapper<ShopResponse, ShopEntity, Void> {
    @Override
    public ShopEntity transform(ShopResponse input, Void params) {
        ShopEntity entity = null;

        if (input != null) {
            entity = new ShopEntity();
            entity.setId(input.getId());
            entity.setName(input.getName());
            entity.setAlias(input.getAlias());
            entity.setImageUrl(input.getImageUrl());
        }

        return entity;
    }
}
