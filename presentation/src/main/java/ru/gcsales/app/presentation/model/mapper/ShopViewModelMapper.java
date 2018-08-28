package ru.gcsales.app.presentation.model.mapper;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.mapper.AbstractMapper;
import ru.gcsales.app.presentation.model.ShopViewModel;

/**
 * Mapper from shop domain model to shop view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShopViewModelMapper extends AbstractMapper<Shop, ShopViewModel, Void> {

    @Override
    public ShopViewModel transform(Shop input, Void params) {
        ShopViewModel shopViewModel = null;

        if (input != null) {
            shopViewModel = new ShopViewModel();
            shopViewModel.setId(input.getId());
            shopViewModel.setName(input.getName());
            shopViewModel.setAlias(input.getAlias());
            shopViewModel.setImageUrl(input.getImageUrl());
        }

        return shopViewModel;
    }
}
