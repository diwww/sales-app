package ru.gcsales.app.data.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.entity.ShopEntity;
import ru.gcsales.app.domain.model.Shop;

/**
 * Mapper class, which transforms
 * shop entities to domain models.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopEntityDataMapper {

    /**
     * Transforms a single entity.
     *
     * @param shopEntity entity to transform
     * @return {@link Shop} domain model
     */
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

    /**
     * Transforms a list of entities.
     *
     * @param shopEntities list of entities to transform
     * @return a list of {@link Shop} domain models
     */
    public List<Shop> transform(List<ShopEntity> shopEntities) {
        List<Shop> shops;

        if (shopEntities != null && !shopEntities.isEmpty()) {
            shops = new ArrayList<>();
            for (ShopEntity shopEntity : shopEntities) {
                shops.add(transform(shopEntity));
            }
        } else {
            return Collections.emptyList();
        }

        return shops;
    }
}
