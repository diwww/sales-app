package ru.gcsales.app.mapper.entity;

import ru.gcsales.app.data.entity.ShopEntity;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.mapper.DataMapper;

/**
 * Mapper class, which transforms
 * shop entities to domain models.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopEntityDataMapper extends DataMapper<ShopEntity, Shop> {

    /**
     * Transforms a single entity.
     *
     * @param shopEntity entity to transform
     * @return {@link Shop} domain model
     */
    @Override
    public Shop transform(ShopEntity shopEntity) {
        Shop shop = null;
        if (shopEntity != null) {
            shop = new Shop();
            shop.setId(shopEntity.getId());
            shop.setAlias(shopEntity.getAlias());
            shop.setName(shopEntity.getName());
            shop.setImageUrl(shopEntity.getImageUrl());
        }
        return shop;
    }
}
