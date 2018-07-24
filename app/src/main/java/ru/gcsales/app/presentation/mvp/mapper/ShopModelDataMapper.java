package ru.gcsales.app.presentation.mvp.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.mvp.model.ShopModel;

public class ShopModelDataMapper {
    public ShopModel transform(Shop shop) {
        ShopModel shopModel = new ShopModel();

        shopModel.setId(shop.getId());
        shopModel.setName(shop.getName());
        shopModel.setAlias(shop.getAlias());
        shopModel.setImageUrl(shop.getImageUrl());

        return shopModel;
    }

    public List<ShopModel> transform(List<Shop> shops) {
        List<ShopModel> shopModels;

        if (shops != null && !shops.isEmpty()) {
            shopModels = new ArrayList<>();
            for (Shop shop : shops) {
                shopModels.add(transform(shop));
            }
        } else {
            shopModels = Collections.emptyList();
        }

        return shopModels;
    }
}
