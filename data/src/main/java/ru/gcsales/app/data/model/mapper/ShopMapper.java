package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.ShopEntity;
import ru.gcsales.app.data.model.remote.ShopResponse;
import ru.gcsales.app.domain.model.Shop;

/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
public class ShopMapper {

    public ShopEntity transformResponse(ShopResponse response) {
        ShopEntity entity = null;

        if (response != null) {
            entity = new ShopEntity();
            entity.setId(response.getId());
            entity.setName(response.getName());
            entity.setAlias(response.getAlias());
            entity.setImageUrl(response.getImageUrl());
        }

        return entity;
    }

    public Shop transformEntity(ShopEntity entity) {
        Shop shop = null;

        if (entity != null) {
            shop = new Shop();
            shop.setId(entity.getId());
            shop.setName(entity.getName());
            shop.setAlias(entity.getAlias());
            shop.setImageUrl(entity.getImageUrl());
        }

        return shop;
    }

    public List<ShopEntity> transformResponse(List<ShopResponse> responseList) {
        List<ShopEntity> entityList;

        if (responseList != null && !responseList.isEmpty()) {
            entityList = new ArrayList<>();
            for (ShopResponse response : responseList) {
                entityList.add(transformResponse(response));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }

    public List<Shop> transformEntity(List<ShopEntity> entityList) {
        List<Shop> shopList;

        if (entityList != null && !entityList.isEmpty()) {
            shopList = new ArrayList<>();
            for (ShopEntity entity : entityList) {
                shopList.add(transformEntity(entity));
            }
        } else {
            return Collections.emptyList();
        }

        return shopList;
    }
}
