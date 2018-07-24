package ru.gcsales.app.presentation.mvp.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.mvp.model.ShopModel;

/**
 * Mapper class, which transforms
 * shop domain models to presentation models.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopModelDataMapper {

    /**
     * Transforms a single model.
     *
     * @param shop model to transform
     * @return {@link ShopModel} presentation model
     */
    public ShopModel transform(Shop shop) {
        ShopModel shopModel = null;
        if (shop != null) {
            shopModel = new ShopModel();
            shopModel.setId(shop.getId());
            shopModel.setName(shop.getName());
            shopModel.setAlias(shop.getAlias());
            shopModel.setImageUrl(shop.getImageUrl());
        }
        return shopModel;
    }

    /**
     * Transforms a list of models.
     *
     * @param shops list of models to transform
     * @return list of {@link ShopModel} presentation models
     */
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
