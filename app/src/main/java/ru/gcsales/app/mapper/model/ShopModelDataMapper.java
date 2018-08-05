package ru.gcsales.app.mapper.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.mapper.DataMapper;
import ru.gcsales.app.presentation.mvp.model.ShopModel;

/**
 * Mapper class, which transforms
 * shop domain models to presentation models.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopModelDataMapper extends DataMapper<Shop, ShopModel> {

    /**
     * Transforms a single model.
     *
     * @param shop model to transform
     * @return {@link ShopModel} presentation model
     */
    @Override
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
}
