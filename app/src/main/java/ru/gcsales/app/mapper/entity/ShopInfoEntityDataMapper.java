package ru.gcsales.app.mapper.entity;

import ru.gcsales.app.data.entity.ShopInfoEntity;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.mapper.DataMapper;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfoEntityDataMapper extends DataMapper<ShopInfoEntity, ShopInfo> {

    private ShopEntityDataMapper mShopEntityDataMapper;

    public ShopInfoEntityDataMapper(ShopEntityDataMapper shopEntityDataMapper) {
        mShopEntityDataMapper = shopEntityDataMapper;
    }

    @Override
    public ShopInfo transform(ShopInfoEntity input) {
        ShopInfo shopInfo = null;

        if (input != null) {
            shopInfo = new ShopInfo();
            shopInfo.setCategories(input.getCategories());
            shopInfo.setNumItems(input.getNumItems());
            shopInfo.setShop(mShopEntityDataMapper.transform(input.getShopEntity()));
        }

        return shopInfo;
    }
}
