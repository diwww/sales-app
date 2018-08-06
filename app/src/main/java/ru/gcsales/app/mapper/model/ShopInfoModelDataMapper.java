package ru.gcsales.app.mapper.model;

import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.mapper.DataMapper;
import ru.gcsales.app.presentation.mvp.model.ShopInfoModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfoModelDataMapper extends DataMapper<ShopInfo, ShopInfoModel> {

    private ShopModelDataMapper mShopModelDataMapper;

    public ShopInfoModelDataMapper(ShopModelDataMapper shopModelDataMapper) {
        mShopModelDataMapper = shopModelDataMapper;
    }

    @Override
    public ShopInfoModel transform(ShopInfo input) {
        ShopInfoModel shopInfoModel = null;

        if (input != null) {
            shopInfoModel = new ShopInfoModel();
            shopInfoModel.setCategories(input.getCategories());
            shopInfoModel.setShop(mShopModelDataMapper.transform(input.getShop()));
        }

        return shopInfoModel;
    }
}
