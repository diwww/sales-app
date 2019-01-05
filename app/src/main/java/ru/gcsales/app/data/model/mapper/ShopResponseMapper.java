package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.remote.ShopResponse;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;

/**
 * Shop response mapper
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ShopResponseMapper extends AbstractMapper<ShopResponse, Shop, Void> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Shop transform(ShopResponse input, Void params) throws Exception {
        return new Shop()
                .setId(input.getId())
                .setName(input.getName())
                .setImageUrl(input.getImageUrl());
    }
}
