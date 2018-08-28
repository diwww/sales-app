package ru.gcsales.app.data.model.mapper;

import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.domain.model.Shop;

/**
 * Shop entity mapper.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShopEntityMapper extends AbstractMapper<ShopEntity, Shop, Void> {
    @Override
    public Shop transform(ShopEntity input, Void params) {
        Shop shop = null;

        if (input != null) {
            shop = new Shop();
            shop.setId(input.getId());
            shop.setName(input.getName());
            shop.setAlias(input.getAlias());
            shop.setImageUrl(input.getImageUrl());
        }

        return shop;
    }
}
